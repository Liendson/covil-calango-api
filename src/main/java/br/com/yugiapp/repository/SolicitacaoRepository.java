package br.com.yugiapp.repository;

import br.com.yugiapp.model.Solicitacao;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    Optional<Solicitacao> findBySessionId(String id);

    List<Solicitacao> findAll(Specification<Solicitacao> specification);
}
