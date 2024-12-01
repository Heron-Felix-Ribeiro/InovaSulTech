package br.com.senac.inovasultech.useCases.agentes;

import br.com.senac.inovasultech.dto.AgenteCreateDTO;
import br.com.senac.inovasultech.dto.AgenteListDTO;
import br.com.senac.inovasultech.dto.EnderecoDTO;
import br.com.senac.inovasultech.entitys.Agente;
import br.com.senac.inovasultech.entitys.Endereco;
import br.com.senac.inovasultech.entitys.TipoAgente;
import br.com.senac.inovasultech.useCases.agentes.domains.AgenteResponseDom;
import br.com.senac.inovasultech.useCases.agentes.implement.mapper.AgenteMapper;
import br.com.senac.inovasultech.useCases.agentes.implement.repository.AgenteRepository;
import br.com.senac.inovasultech.useCases.endereco.implement.repository.EnderecoRepository;
import br.com.senac.inovasultech.useCases.tipoAgente.implement.repository.TipoAgenteRepository;
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

    public AgenteResponseDom criarAgente(AgenteCreateDTO agente) throws Exception {

        Agente agentePersist = agenteCreateDtoToAgente(agente);

        agenteRepository.save(agentePersist);

        AgenteResponseDom response = AgenteMapper.agenteToAgenteResponseDom(agentePersist);

        return response;


    }

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

    public AgenteListDTO listarUmAgente(Long id) throws Exception {

        Optional<Agente> agenteResult = agenteRepository.findById(id);

        if (!agenteResult.isEmpty()){

            Agente agente = agenteResult.get();

            AgenteListDTO response = agenteToAgenteListDto(agente);

            return response;
        }

        throw new Exception("Não foi possível encontrar o agente, verifique se o agente informado está correto");
    }

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

    public AgenteResponseDom atualizarAgente (Long id, AgenteCreateDTO agente) throws Exception {

        Optional<Agente> optionalAgente = agenteRepository.findById(id);

        if (!optionalAgente.isEmpty()){

            Agente agenteExist = optionalAgente.get();

            agenteExist = agenteCreateDtoToAgente(agente);
            agenteExist.setId(optionalAgente.get().getId());


            agenteRepository.save(agenteExist);

            AgenteResponseDom response = AgenteMapper.agenteToAgenteResponseDom(agenteExist);

            return response;
        }

        throw new Exception("O agente não existe");
    }

    public void deletarAgente (Long agenteId) throws Exception {

        Optional<Agente> optionalAgente = agenteRepository.findById(agenteId);

        if (!optionalAgente.isEmpty()) {

            Agente agente = optionalAgente.get();

            //enderecoRepository.deleteAllByAgenteId(agenteId);

            agenteRepository.delete(agente);


            return;
        }

        throw new Exception("Agente não existe");
    }

    public Agente agenteCreateDtoToAgente(AgenteCreateDTO in) {

        Agente out = new Agente();
        out.setNomeAgente(in.getNomeAgente());
        out.setCnpj(in.getCnpj());
        out.setDescricao(in.getDescricao());

        TipoAgente tipoAgente = tipoAgenteRepository.findByNmTipoAgente(in.getTipoAgente())
                .orElseThrow(() -> new RuntimeException("Tipo Agente não encontrado"));
        out.setTipoAgente(tipoAgente);

        Endereco endereco = enderecoRepository.findByRua(in.getEndereco())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        List<Endereco> listaDeEnderecos = new ArrayList<>();
        listaDeEnderecos.add(endereco);

        out.setEnderecos((List<Endereco>) listaDeEnderecos);

        return out;
    }

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
