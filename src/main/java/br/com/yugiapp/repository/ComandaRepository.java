package br.com.yugiapp.repository;

import br.com.yugiapp.model.Comanda;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

    Optional<Comanda> findByNumero(String numero);

    List<Comanda> findAll(Specification<Comanda> specification);
}
