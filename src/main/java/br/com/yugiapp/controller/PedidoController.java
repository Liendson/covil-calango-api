package br.com.yugiapp.controller;

import br.com.yugiapp.dto.PedidoDTO;
import br.com.yugiapp.dto.PedidoFilterDTO;
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
    public void salvar(@RequestBody PedidoDTO pedido) {
        pedidoService.save(pedido);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        pedidoService.delete(id);
    }

    @GetMapping
    public List<Pedido> getAll(PedidoFilterDTO pedidoFilterDTO) {
        return pedidoService.getAllByFilters(pedidoFilterDTO);
    }

    @GetMapping(path = "{id}")
    public Pedido getById(@PathVariable Long id) {
        return pedidoService.getById(id);
    }
}
