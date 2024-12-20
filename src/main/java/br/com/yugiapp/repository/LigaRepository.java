package br.com.yugiapp.repository;

import br.com.yugiapp.model.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LigaRepository extends JpaRepository<Liga, Long> {

    @Query(value = "SELECT l from Liga l where l.vigente is true")
    Optional<Liga> findVigente();
}
