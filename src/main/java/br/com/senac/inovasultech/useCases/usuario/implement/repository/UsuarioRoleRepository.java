package br.com.senac.inovasultech.useCases.usuario.implement.repository;

import br.com.senac.inovasultech.entitys.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRoleRepository extends JpaRepository<Roles, Long> {

    List<Roles> findByPermissaoIn(List<String> permissoes);

    Optional<Roles> findByPermissao(String permissao);

}
