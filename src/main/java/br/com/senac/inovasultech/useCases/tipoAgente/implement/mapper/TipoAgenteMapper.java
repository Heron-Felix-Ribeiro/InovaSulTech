package br.com.senac.inovasultech.useCases.tipoAgente.implement.mapper;

import br.com.senac.inovasultech.entitys.TipoAgente;
import br.com.senac.inovasultech.useCases.tipoAgente.domains.TipoAgenteRequestDom;
import br.com.senac.inovasultech.useCases.tipoAgente.domains.TipoAgenteResponseDom;

public class TipoAgenteMapper {

    public static TipoAgente tipoAgenteRequestToTipoAgente (TipoAgenteRequestDom in) {

        TipoAgente out = new TipoAgente();
        out.setNmTipoAgente(in.getNmTipoAgente());

        return out;
    }

    public static TipoAgenteResponseDom tipoAgenteToTipoAgenteResponse (TipoAgente in) {

        TipoAgenteResponseDom out = new TipoAgenteResponseDom();
        out.setId(in.getId());
        out.setNmTipoAgente(in.getNmTipoAgente());

        return out;
    }
}
