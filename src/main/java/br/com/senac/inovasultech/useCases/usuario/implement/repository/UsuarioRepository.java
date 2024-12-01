package br.com.senac.inovasultech.useCases.usuario.implement.repository;

import br.com.senac.inovasultech.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);


}
