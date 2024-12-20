package br.com.yugiapp.service;

import br.com.yugiapp.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoService {

    Page<Pedido> getAll(Pageable pageable);

    List<Pedido> getAll();

    Pedido getById(Long id);

    void delete(Long id);

    Pedido save(Pedido pedido);
}
