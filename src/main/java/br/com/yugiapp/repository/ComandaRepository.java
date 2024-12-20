package br.com.yugiapp.repository;

import br.com.yugiapp.model.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

    Comanda findByNumero(String numero);
}
