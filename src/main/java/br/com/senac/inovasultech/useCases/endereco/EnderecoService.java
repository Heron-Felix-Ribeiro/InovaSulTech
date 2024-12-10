package br.com.senac.inovasultech.useCases.endereco;

import br.com.senac.inovasultech.dto.EnderecoDTO;
import br.com.senac.inovasultech.entitys.Cidade;
import br.com.senac.inovasultech.entitys.Endereco;
import br.com.senac.inovasultech.useCases.cidade.implement.repository.CidadeRepository;
import br.com.senac.inovasultech.useCases.endereco.domains.EnderecoResponseDom;
import br.com.senac.inovasultech.useCases.endereco.implement.mapper.EnderecoMapper;
import br.com.senac.inovasultech.useCases.endereco.implement.repository.EnderecoRepository;
import br.com.senac.inovasultech.utils.CepUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;
    private EnderecoDTO enderecoToEnderecoDto (Endereco in) {

        EnderecoDTO out = new EnderecoDTO();
        out.setId(in.getId());
        out.setRua(in.getRua());
        out.setBairro(in.getBairro());
        out.setBairro(in.getBairro());
        out.setCep(in.getCep());
        out.setCidade(in.getCidade().getNomeCidade());



        return out;
    }

    public EnderecoResponseDom criarEndereco (EnderecoDTO endereco) {

        Endereco enderecoPersist = enderecoDtoToEndereco(endereco);

        enderecoRepository.save(enderecoPersist);

        EnderecoResponseDom response = EnderecoMapper.enderecoToEnderecoResponseDom(enderecoPersist);

        return response;
    }

    public List<EnderecoDTO> listarEnderecos () throws Exception {

        List<Endereco> result = enderecoRepository.findAll();

        if (!result.isEmpty()){

            return result
                    .stream()
                    .map(endereco -> { try
                        {return enderecoToEnderecoDto(endereco);}

                    catch (Exception e) {
                        throw new RuntimeException ("Não foi possível converter os dados", e);}
                    })
                    .toList();
        }

        throw new Exception("Não foi possível encontra enderecos");
    }

    public EnderecoDTO listarUmEndereco (Long id) throws Exception {

        Optional<Endereco> enderecoResult = enderecoRepository.findById(id);

        if (!enderecoResult.isEmpty()){

            Endereco endereco = enderecoResult.get();

            EnderecoDTO response = enderecoToEnderecoDto(endereco);

            return response;
        }

        throw new Exception("Não foi possível encontra o endereço");
    }

    public EnderecoResponseDom atualizarEndereco (Long id, EnderecoDTO endereco) throws Exception {

        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);

        if (!optionalEndereco.isEmpty()) {

            Endereco enderecoExist = optionalEndereco.get();

            enderecoExist = enderecoDtoToEndereco(endereco);
            enderecoExist.setId(optionalEndereco.get().getId());

            enderecoRepository.save(enderecoExist);

            EnderecoResponseDom response = EnderecoMapper.enderecoToEnderecoResponseDom(enderecoExist);

            return response;
        }

        throw new Exception("O endereço não existe");
    }

    public void deletarEndereco (Long EnderecoId) throws Exception {

        Optional<Endereco> optionalEndereco = enderecoRepository.findById(EnderecoId);

        if (!optionalEndereco.isEmpty()) {

            Endereco endereco = optionalEndereco.get();

            enderecoRepository.delete(endereco);

            return;
        }

        throw new Exception("O endereço não existe");
    }

    public Endereco enderecoDtoToEndereco (EnderecoDTO in) {

        if (!CepUtils.CepValido(in.getCep())) {
            throw new RuntimeException("CEP inválido");
        }

        Endereco out = new Endereco();
        out.setRua(in.getRua());
        out.setBairro(in.getBairro());
        out.setCep(in.getCep());
        Cidade cidade = cidadeRepository.findByNomeCidade(in.getCidade())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        out.setCidade(cidade);

        return out;
    }
}
