package br.com.senac.inovasultech.useCases.agentes.implement.mapper;


import br.com.senac.inovasultech.entitys.Agente;
import br.com.senac.inovasultech.useCases.agentes.domains.AgenteRequestDom;
import br.com.senac.inovasultech.useCases.agentes.domains.AgenteResponseDom;

public class AgenteMapper {

    public static Agente agenteRequestDomToAgente (AgenteRequestDom in) {

        Agente out = new Agente();
        out.setNomeAgente(in.getNomeAgente());
        out.setCnpj(in.getCnpj());
        out.setDescricao(in.getDescricao());
        out.setTipoAgente(in.getTipoAgente());
        out.setEnderecos(in.getEnderecos());

        return out;
    }

    public static AgenteResponseDom agenteToAgenteResponseDom (Agente in) {

        AgenteResponseDom out = new AgenteResponseDom();
        out.setId(in.getId());
        out.setNomeAgente(in.getNomeAgente());
        out.setCnpj(in.getCnpj());
        out.setDescricao(in.getDescricao());
        out.setTipoAgente(in.getTipoAgente());
        out.setEnderecos(in.getEnderecos());

        return out;
    }

}
