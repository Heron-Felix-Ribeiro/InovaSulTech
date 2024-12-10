package br.com.senac.inovasultech.useCases.cidade;

import br.com.senac.inovasultech.dto.CidadeDTO;
import br.com.senac.inovasultech.entitys.Cidade;
import br.com.senac.inovasultech.useCases.cidade.domains.CidadeRequestDom;
import br.com.senac.inovasultech.useCases.cidade.domains.CidadeResponseDom;
import br.com.senac.inovasultech.useCases.cidade.implement.mapper.CidadeMapper;
import br.com.senac.inovasultech.useCases.cidade.implement.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;


    //Criando uma cidade
    public CidadeResponseDom criaCidade (CidadeRequestDom cidade){

        Cidade cidadePersist = CidadeMapper.cidadeRequestToCidade(cidade);

        cidadeRepository.save(cidadePersist);

        CidadeResponseDom response = CidadeMapper.cidadeToCidadeResponseDom(cidadePersist);

        return response;
    }

    //Listando todas as cidades
    public List<CidadeResponseDom> listarCidades() throws Exception {

        List<Cidade> cidadadeResult = cidadeRepository.findAll();
        System.out.println("Cidades encontradas: " + cidadadeResult);

        if (!cidadadeResult.isEmpty()){

            List<CidadeResponseDom> cidadeResponse =
                    cidadadeResult.stream()
                            .map(row -> new CidadeResponseDom(
                                    row.getId(),
                                    row.getNomeCidade(),
                                    row.getEstado()))
                            .toList();

            return cidadeResponse;
        }

        throw new Exception("Não foi possível encontrar cidades cadastradas");
    }

    //Listando apenas uma cidade com base no id fornecido
    public CidadeDTO listarUmaCidade (Long id) throws Exception {

        Optional<Cidade> cidadeResult = cidadeRepository.findById(id);

        if (!cidadeResult.isEmpty()) {

            Cidade cidade = cidadeResult.get();

            CidadeDTO response = cidadeToCidadeDto(cidade);

            return response;
        }

        throw new Exception("Cidade não encontrada");
    }

    //Deletando uma cidade pelo ID
    public void deletarCidade (Long cidadeId){

        Optional<Cidade> optionalCidade = cidadeRepository.findById(cidadeId);

        if (!optionalCidade.isEmpty()){

            Cidade cidade = optionalCidade.get();

            cidadeRepository.delete(cidade);

            return;
        }


        throw new RuntimeException("Cidade não encontrada");
    }

    public CidadeDTO cidadeToCidadeDto (Cidade in) {

        CidadeDTO out = new CidadeDTO();
        out.setId(in.getId());
        out.setNomeCidade(in.getNomeCidade());
        out.setEstado(in.getEstado());

        return out;
    }

}
