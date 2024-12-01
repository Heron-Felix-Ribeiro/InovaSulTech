package br.com.senac.inovasultech.useCases.endereco.implement.mapper;

import br.com.senac.inovasultech.entitys.Endereco;
import br.com.senac.inovasultech.useCases.endereco.domains.EnderecoRequestDom;
import br.com.senac.inovasultech.useCases.endereco.domains.EnderecoResponseDom;

public class EnderecoMapper {

    public static Endereco enderecoRequestDomToEndereco (EnderecoRequestDom in) {

        Endereco out = new Endereco();
        out.setRua(in.getRua());
        out.setBairro(in.getBairro());
        out.setCep(in.getCep());
        out.setCidade(in.getCidade());

        return out;
    }



    public static EnderecoResponseDom enderecoToEnderecoResponseDom (Endereco in) {

        EnderecoResponseDom out = new EnderecoResponseDom();
        out.setId(in.getId());
        out.setRua(in.getRua());
        out.setBairro(in.getBairro());
        out.setCep(in.getCep());
        out.setCidade(in.getCidade());

        return out;
    }


}
