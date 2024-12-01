package br.com.senac.inovasultech.useCases.agentes.implement.mapper;

import br.com.senac.inovasultech.dto.AgenteListDTO;
import br.com.senac.inovasultech.dto.EnderecoDTO;
import br.com.senac.inovasultech.entitys.Agente;
import br.com.senac.inovasultech.useCases.agentes.domains.AgenteRequestDom;
import br.com.senac.inovasultech.useCases.agentes.domains.AgenteResponseDom;

import java.util.List;
import java.util.stream.Collectors;

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

   /*public static AgenteListDTO agenteToAgenteDto (Agente in) {

        AgenteListDTO out = new AgenteListDTO();
        out.setNomeAgente(in.getNomeAgente());
        out.setCnpj(in.getCnpj());
        out.setDescricao(in.getDescricao());
        out.setTipoAgente(in.getTipoAgente().getNmTipoAgente());
        List<EnderecoDTO> enderecoDto = in.getEnderecos().stream()
                        .map(endereco -> new EnderecoDTO(endereco))
                                .collect(Collectors.toList());
        out.setEnderecos(enderecoDto);

        return out;
    }*/
}
