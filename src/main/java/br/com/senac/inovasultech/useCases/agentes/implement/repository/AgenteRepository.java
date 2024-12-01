package br.com.senac.inovasultech.useCases.agentes.implement.repository;

import br.com.senac.inovasultech.entitys.Agente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgenteRepository extends JpaRepository<Agente, Long> {

    List<Agente> findByTipoAgenteId(Long tipoAgente);
}
