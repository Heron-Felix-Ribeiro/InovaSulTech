package br.com.senac.inovasultech.useCases.endereco.implement.repository;

import br.com.senac.inovasultech.entitys.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByCep(String cep);
    Optional<Endereco> findByRuaAndBairroAndCidade_NomeCidade(String rua, String bairro, String cidade);


}
