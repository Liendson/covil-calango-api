package br.com.yugiapp.service.impl;

import br.com.yugiapp.enums.StatusPedidoEnum;
import br.com.yugiapp.model.Pedido;
import br.com.yugiapp.repository.PedidoRepository;
import br.com.yugiapp.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public Page<Pedido> getAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @Override
    public List<Pedido> getAll() {
        return pedidoRepository.findAllPendentes();
    }

    @Override
    public Pedido getById(Long id) {
        return pedidoRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(StatusPedidoEnum.EM_ANDAMENTO);
        return pedidoRepository.save(pedido);
    }
}
