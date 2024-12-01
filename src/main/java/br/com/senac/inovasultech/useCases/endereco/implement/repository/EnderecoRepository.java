package br.com.senac.inovasultech.useCases.endereco.implement.repository;

import br.com.senac.inovasultech.entitys.Endereco;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByRua (String rua);

    /*@Transactional
    void deleteAllByAgenteId(Long id);*/
}
