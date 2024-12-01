package br.com.senac.inovasultech.useCases.usuario;

import br.com.senac.inovasultech.entitys.Roles;
import br.com.senac.inovasultech.entitys.Usuario;
import br.com.senac.inovasultech.jwt.TokenService;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioLoginRequestDom;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioLoginResponseDom;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioRequestDom;
import br.com.senac.inovasultech.useCases.usuario.domains.UsuarioResponseDom;
import br.com.senac.inovasultech.useCases.usuario.implement.mapper.UsuarioMapper;
import br.com.senac.inovasultech.useCases.usuario.implement.repository.UsuarioRepository;
import br.com.senac.inovasultech.useCases.usuario.implement.repository.UsuarioRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRoleRepository usuarioRoleRepository;

    @Transactional
    public UsuarioResponseDom criarUsuario(UsuarioRequestDom usuario) throws Exception {
        Optional<Usuario> resultLogin = usuarioRepository.findByLogin(usuario.getLogin());

        if (resultLogin.isPresent()) {
            throw new Exception("Usuário já cadastrado!");
        }

        Usuario usuarioPersist = UsuarioMapper.usuarioRequestDomParaUsuario(usuario);

        usuarioPersist.setSenha(passwordEncoder.encode(usuario.getSenha()));

        if (usuario.getPermissoes() == null || usuario.getPermissoes().isEmpty()) {

            Optional<Roles> userRole = usuarioRoleRepository.findByPermissao("USER");

            Roles role;
            if (userRole.isPresent()) {
                role = userRole.get();
            }
            else {
                role = new Roles();
                role.setPermissao("USER");
                usuarioRoleRepository.save(role); }
            usuarioPersist.setPermissoes(Collections.singletonList(role));
        }

        Usuario usuarioPersistResult = usuarioRepository.save(usuarioPersist);
        String token = tokenService.gerarToken(usuarioPersistResult);
        UsuarioResponseDom response = UsuarioMapper.usuarioParaUsuarioResponseDom(usuarioPersistResult);

        response.setToken(token);

        return response;
    }


    public UsuarioLoginResponseDom login (UsuarioLoginRequestDom usuarioLogin) throws Exception {

        Optional<Usuario> usuarioResult = usuarioRepository.findByLogin(usuarioLogin.getLogin());

        if (!usuarioResult.isPresent()){

            throw new Exception("Usuário encontrado");
        }

        Usuario usuario = usuarioResult.get();

        if (passwordEncoder.matches(
                usuarioLogin.getSenha(),
                usuario.getSenha())){

            String token = tokenService.gerarToken(usuario);

            return new UsuarioLoginResponseDom(
                    usuario.getLogin(),
                    token);

        }

        throw new Exception("Senha invalida");
    }

    public List<UsuarioResponseDom> carregarUsuarios () throws Exception {

        List<Usuario> usuarioResult = usuarioRepository.findAll();

        if (!usuarioResult.isEmpty()){

            return usuarioResult
                    .stream()
                    .map(UsuarioMapper::usuarioParaUsuarioResponseDom)
                    .toList();
        }

        throw new Exception("Sem usuarios para listar");
    }

    /*public UsuarioResponseDom atualizarUsuario (Long id, UsuarioRequestDom usuario){

    }*/

    public void deletaraUsuario (Long UsuarioId) throws Exception {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(UsuarioId);

        if (!optionalUsuario.isEmpty()){

            Usuario usuario = optionalUsuario.get();

            usuarioRepository.delete(usuario);

            return;
        }
    }
}
