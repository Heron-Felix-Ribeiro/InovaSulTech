package br.com.senac.inovasultech.config;

import br.com.senac.inovasultech.entitys.Roles;
import br.com.senac.inovasultech.entitys.Usuario;
import br.com.senac.inovasultech.jwt.TokenService;
import br.com.senac.inovasultech.useCases.usuario.implement.repository.UsuarioRepository;
import br.com.senac.inovasultech.useCases.usuario.implement.repository.UsuarioRoleRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRoleRepository usuarioRoleRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null && tokenService.validarToken(token) != null) {
            String login = tokenService.validarToken(token);
            Usuario usuario = usuarioRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


            Optional<Roles> role = usuarioRoleRepository.findByPermissao("USER");

            if (role.isPresent()) {
                System.out.println("Role 'USER' encontrada no banco de dados: " + role.get().getPermissao());
            }
            else {

                Roles newRole = new Roles();
                newRole.setPermissao("USER");

                try {
                    usuarioRoleRepository.saveAndFlush(newRole);
                } catch (Exception e) {

                    newRole = usuarioRoleRepository.findByPermissao("USER").orElseThrow(() -> new RuntimeException("Erro ao buscar Role 'USER' após exceção"));
                }
                role = Optional.of(newRole);
                System.out.println("Role 'USER' criada com sucesso");
            }

            var authorities = Collections.singletonList(new SimpleGrantedAuthority(role.get().getPermissao()));
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
