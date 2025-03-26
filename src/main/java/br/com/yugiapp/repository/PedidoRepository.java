package br.com.yugiapp.repository;

import br.com.yugiapp.model.Pedido;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT p from Pedido p where p.status = 'EM_ANDAMENTO'")
    List<Pedido> findAllPendentes();

    List<Pedido> findAll(Specification<Pedido> specification);
}
