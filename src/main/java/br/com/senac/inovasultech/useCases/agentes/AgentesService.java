package br.com.senac.inovasultech.useCases.agentes;

import br.com.senac.inovasultech.dto.AgenteCreateDTO;
import br.com.senac.inovasultech.dto.AgenteListDTO;
import br.com.senac.inovasultech.dto.AgenteUpdateDTO;
import br.com.senac.inovasultech.dto.EnderecoDTO;
import br.com.senac.inovasultech.entitys.Agente;
import br.com.senac.inovasultech.entitys.Cidade;
import br.com.senac.inovasultech.entitys.Endereco;
import br.com.senac.inovasultech.entitys.TipoAgente;
import br.com.senac.inovasultech.useCases.agentes.domains.AgenteResponseDom;
import br.com.senac.inovasultech.useCases.agentes.implement.mapper.AgenteMapper;
import br.com.senac.inovasultech.useCases.agentes.implement.repository.AgenteRepository;
import br.com.senac.inovasultech.useCases.cidade.implement.repository.CidadeRepository;
import br.com.senac.inovasultech.useCases.endereco.implement.repository.EnderecoRepository;
import br.com.senac.inovasultech.useCases.tipoAgente.implement.repository.TipoAgenteRepository;
import br.com.senac.inovasultech.utils.CnpjUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentesService {

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private TipoAgenteRepository tipoAgenteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    //Criando um agente
    public AgenteResponseDom criarAgente(AgenteCreateDTO agente) throws Exception {

        Agente agentePersist = agenteCreateDtoToAgente(agente);

        agenteRepository.save(agentePersist);

        AgenteResponseDom response = AgenteMapper.agenteToAgenteResponseDom(agentePersist);

        return response;


    }

    //Listando todos os agentes
    public List<AgenteListDTO> listarAgentes() throws Exception {

        List<Agente> agenteResult = agenteRepository.findAll();

        if (!agenteResult.isEmpty()){

            return agenteResult
                    .stream()
                    .map(agentes -> { try
                        { return agenteToAgenteListDto(agentes);}

                        catch (Exception e) {
                            throw new RuntimeException("Não foi possível converter os agentes");
                        }
                    })
                    .toList();
        }

        throw new Exception("Não foi possível encontrar o agente");
    }

    //Listando apenas um agente com base no id fornecido
    public AgenteListDTO listarUmAgente(Long id) throws Exception {

        Optional<Agente> agenteResult = agenteRepository.findById(id);

        if (!agenteResult.isEmpty()){

            Agente agente = agenteResult.get();

            AgenteListDTO response = agenteToAgenteListDto(agente);

            return response;
        }

        throw new Exception("Não foi possível encontrar o agente, verifique se o agente informado está correto");
    }

    //Listando os agentes com base no tipo agente (Na regra de negócio só devem existir dois, sendo eles Startups e Empresas de Tecnologia)
    public List<AgenteListDTO> listarAgenteByIdTipoAgente(Long idTipoAgente) throws Exception {

        List<Agente> agenteList = agenteRepository.findByTipoAgenteId(idTipoAgente);

        if (!agenteList.isEmpty()){

            return agenteList
                    .stream()
                    .map(agente -> {try {
                            return agenteToAgenteListDto(agente);
                        } catch (Exception e){
                        throw new RuntimeException("Não foi possivel converter os dados", e);
                    }
                    }
                    ).collect(Collectors.toList());
        }

        throw new Exception("Não foi possivel encontrar o agente");
    }

    //Realizando a atualização de um agente pegando seu id, e depois alterando as informações desejadas
    public AgenteResponseDom atualizarAgente (Long id, AgenteUpdateDTO agente) throws Exception {

        Optional<Agente> optionalAgente = agenteRepository.findById(id);

        if (!optionalAgente.isEmpty()){

            Agente agenteExist = optionalAgente.get();

            agenteExist = agenteUpdateDtoToAgente(agente);
            agenteExist.setEnderecos(optionalAgente.get().getEnderecos());
            agenteExist.setId(optionalAgente.get().getId());


            agenteRepository.save(agenteExist);

            AgenteResponseDom response = AgenteMapper.agenteToAgenteResponseDom(agenteExist);

            return response;
        }

        throw new Exception("O agente não existe");
    }

    //Realizando a deleção de um agente com base em seu id
    public void deletarAgente (Long agenteId) throws Exception {

        Optional<Agente> optionalAgente = agenteRepository.findById(agenteId);

        if (!optionalAgente.isEmpty()) {

            Agente agente = optionalAgente.get();

            agenteRepository.delete(agente);


            return;
        }

        throw new Exception("Agente não existe");
    }

    //Conversor de agentes espeficifico para a criação que realiza a busca dos dados em banco para confirmação da sua existência
    public Agente agenteCreateDtoToAgente(AgenteCreateDTO in) {

        if (!CnpjUtils.cpnjValido(in.getCnpj())){
            throw new RuntimeException("CNPJ inválido");
        }

        Agente out = new Agente();
        out.setNomeAgente(in.getNomeAgente());
        out.setCnpj(in.getCnpj());
        out.setDescricao(in.getDescricao());

        TipoAgente tipoAgente = tipoAgenteRepository.findByNmTipoAgente(in.getTipoAgente())
                .orElseThrow(() -> new RuntimeException("Tipo Agente não encontrado"));
        out.setTipoAgente(tipoAgente);

        Endereco endereco;
        if (in.getEndereco().matches("\\d{5}-\\d{3}")) {
            endereco = enderecoRepository.findByCep(in.getEndereco())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        } else {
            String [] partesEndereco = in.getEndereco().split(",");
            if (partesEndereco.length < 3) {
                throw new RuntimeException("Endereço incompleto");
            }
            if (partesEndereco.length > 3) {
                throw new RuntimeException("Você só precisa inserir rua, bairro e cidade");
            }

            String rua = partesEndereco[0].trim();
            String bairro = partesEndereco[1].trim();
            String nomeCidade = partesEndereco[2].trim();

            Cidade cidade = cidadeRepository.findByNomeCidade(nomeCidade)
                    .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

            endereco = enderecoRepository.findByRuaAndBairroAndCidade_NomeCidade(rua, bairro, cidade.getNomeCidade())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado pelos detalhes fornecidos"));
        }

        List<Endereco> listaDeEnderecos = new ArrayList<>();
        listaDeEnderecos.add(endereco);

        out.setEnderecos((List<Endereco>) listaDeEnderecos);

        return out;
    }

    public Agente agenteUpdateDtoToAgente(AgenteUpdateDTO in) {

        if (!CnpjUtils.cpnjValido(in.getCnpj())) {
            throw new RuntimeException("CNPJ inválido");
        }

        Agente out = new Agente();
        out.setNomeAgente(in.getNomeAgente());
        out.setCnpj(in.getCnpj());
        out.setDescricao(in.getDescricao());
        TipoAgente tipoAgente = tipoAgenteRepository.findByNmTipoAgente(in.getTipoAgente())
                .orElseThrow(() -> new RuntimeException("Tipo Agente não encontrado"));
        out.setTipoAgente(tipoAgente);

        return out;
    }

    //Conversor de agentes especifico para listagem
    public AgenteListDTO agenteToAgenteListDto(Agente in) {

        AgenteListDTO out = new AgenteListDTO();
        out.setId(in.getId());
        out.setNomeAgente(in.getNomeAgente());
        out.setCnpj(in.getCnpj());
        out.setDescricao(in.getDescricao());
        out.setTipoAgente(in.getTipoAgente().getNmTipoAgente());
        List<EnderecoDTO> enderecoDto = in.getEnderecos().stream()
                .map(endereco -> new EnderecoDTO(endereco))
                .collect(Collectors.toList());
        out.setEnderecos(enderecoDto);


        return out;
    }

}
