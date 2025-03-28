package br.com.yugiapp.repository;

import br.com.yugiapp.model.Torneio;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TorneioRepository extends JpaRepository<Torneio, Long> {

    List<Torneio> findAll(Specification<Torneio> specification);
}
