package br.com.yugiapp.service;

import br.com.yugiapp.dto.PedidoRequestDTO;
import br.com.yugiapp.dto.PedidoFilterRequestDTO;
import br.com.yugiapp.enums.StatusPedidoEnum;
import br.com.yugiapp.model.Pedido;
import br.com.yugiapp.model.Produto;
import br.com.yugiapp.repository.PedidoRepository;
import br.com.yugiapp.specs.PedidoSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ComandaService comandaService;
    private final PedidoSpecifications pedidoSpecifications;

    public Page<Pedido> getAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public List<Pedido> getAllByFilters(PedidoFilterRequestDTO pedidoFilterRequestDTO) {
        return pedidoRepository.findAll(pedidoSpecifications.getSpecs(pedidoFilterRequestDTO));
    }

    public List<Pedido> getAll() {
        return pedidoRepository.findAllPendentes();
    }

    public Pedido getById(Long id) {
        return pedidoRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido save(PedidoRequestDTO pedidoRequestDTO) {
        return pedidoRepository.save(Pedido.builder()
                .dataHora(LocalDateTime.now())
                .status(StatusPedidoEnum.EM_ANDAMENTO)
                .produto(Produto.builder().id(pedidoRequestDTO.getProduto().getId()).build())
                .observacao(pedidoRequestDTO.getObservacao())
                .quantidade(pedidoRequestDTO.getQuantidade())
                .comanda(comandaService.getByNumero(pedidoRequestDTO.getComanda()))
                .build());
    }
}
