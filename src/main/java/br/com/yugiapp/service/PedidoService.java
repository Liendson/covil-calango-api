package br.com.yugiapp.service;

import br.com.yugiapp.dto.PedidoDTO;
import br.com.yugiapp.dto.PedidoFilterDTO;
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

    public List<Pedido> getAllByFilters(PedidoFilterDTO pedidoFilterDTO) {
        return pedidoRepository.findAll(pedidoSpecifications.getSpecs(pedidoFilterDTO));
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

    public Pedido save(PedidoDTO pedidoDTO) {
        return pedidoRepository.save(Pedido.builder()
                .dataHora(LocalDateTime.now())
                .status(StatusPedidoEnum.EM_ANDAMENTO)
                .produto(Produto.builder().id(pedidoDTO.getProduto().getId()).build())
                .observacao(pedidoDTO.getObservacao())
                .quantidade(pedidoDTO.getQuantidade())
                .comanda(comandaService.getByNumero(pedidoDTO.getComanda()))
                .build());
    }
}
