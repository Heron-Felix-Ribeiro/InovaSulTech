package br.com.senac.inovasultech.useCases.cidade.implement.mapper;

import br.com.senac.inovasultech.entitys.Cidade;
import br.com.senac.inovasultech.useCases.cidade.domains.CidadeRequestDom;
import br.com.senac.inovasultech.useCases.cidade.domains.CidadeResponseDom;

public class CidadeMapper {

    //Convertendo CidadeRequest para a entidade Cidade
    public static Cidade cidadeRequestToCidade(CidadeRequestDom in) {

        Cidade out = new Cidade();
        out.setNomeCidade(in.getNomeCidade());
        out.setEstado(in.getEstado());

        return out;
    }

    //Convertendo a entidade Cidade para CidadeResponse
    public static CidadeResponseDom cidadeToCidadeResponseDom(Cidade in) {

        CidadeResponseDom out = new CidadeResponseDom();
        out.setId(in.getId());
        out.setNomeCidade(in.getNomeCidade());
        out.setEstado(in.getEstado());

        return out;
    }

}
