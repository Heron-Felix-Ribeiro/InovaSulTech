package br.com.senac.inovasultech.useCases.tipoAgente.implement.repository;

import br.com.senac.inovasultech.entitys.TipoAgente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoAgenteRepository extends JpaRepository<TipoAgente, Long> {

    Optional<TipoAgente> findByNmTipoAgente (String nmTipoAgente);
}
