package br.com.senac.inovasultech.useCases.cidade.implement.repository;

import br.com.senac.inovasultech.entitys.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Optional<Cidade> findByNomeCidade (String nomeCidade);
}
