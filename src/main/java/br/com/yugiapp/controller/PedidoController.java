package br.com.yugiapp.controller;

import br.com.yugiapp.dto.PedidoRequestDTO;
import br.com.yugiapp.dto.PedidoFilterRequestDTO;
import br.com.yugiapp.model.Pedido;
import br.com.yugiapp.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public void salvar(@RequestBody PedidoRequestDTO pedido) {
        // TODO: VALIDAR SE A COZINHA ESTÁ ABERTA
        // TODO: CRIAR MÓDULO DE COZINHA COM STATUS ABERTA E FECHADA PARA RECEBER PEDIDOS
        pedidoService.save(pedido);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        pedidoService.delete(id);
    }

    @GetMapping
    public List<Pedido> getAll(PedidoFilterRequestDTO pedidoFilterRequestDTO) {
        return pedidoService.getAllByFilters(pedidoFilterRequestDTO);
    }

    @GetMapping(path = "{id}")
    public Pedido getById(@PathVariable Long id) {
        return pedidoService.getById(id);
    }
}
