package br.com.yugiapp.repository;

import br.com.yugiapp.model.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {

    Optional<Parametro> findByNome(String nome);
}
