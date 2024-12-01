package br.com.senac.inovasultech.useCases.usuario.implement.mapper;

import br.com.senac.inovasultech.entitys.Usuario;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioRequestDom;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioResponseDom;

public class UsuarioMapper {

    public static Usuario usuarioRequestDomParaUsuario(UsuarioRequestDom in){

        Usuario out = new Usuario();
        out.setLogin(in.getLogin());

        return out;
    }

    public static UsuarioResponseDom
    usuarioParaUsuarioResponseDom(Usuario in){

        UsuarioResponseDom out = new UsuarioResponseDom();
        out.setId(in.getId());
        out.setLogin(in.getLogin());

        if(!in.getPermissoes().isEmpty()){
            out.setPermissoes(in.getPermissoes()
                    .stream()
                    .map(row -> row.getPermissao())
                    .toList());
        }

        return out;

    }
}
