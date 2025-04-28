package br.com.yugiapp.controller;

import br.com.yugiapp.dto.PedidoRequestDTO;
import br.com.yugiapp.dto.PedidoFilterRequestDTO;
import br.com.yugiapp.enums.StatusPedidoEnum;
import br.com.yugiapp.model.Pedido;
import br.com.yugiapp.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public void salvar(@RequestBody PedidoRequestDTO pedido) {
        // TODO: VALIDAR SE A COZINHA ESTÁ ABERTA
        // TODO: CRIAR MÓDULO DE COZINHA COM STATUS ABERTA E FECHADA PARA RECEBER PEDIDOS
        Pedido novoPedido = pedidoService.save(pedido);
        messagingTemplate.convertAndSend("/topic/pedidos", novoPedido);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        pedidoService.delete(id);
    }

    @GetMapping
    public List<Pedido> getAllByFilters(PedidoFilterRequestDTO pedidoFilterRequestDTO) {
        return pedidoService.getAllByFilters(pedidoFilterRequestDTO);
    }

    @GetMapping(path = "{id}")
    public Pedido getById(@PathVariable Long id) {
        return pedidoService.getById(id);
    }

    // TODO: ADICIONAR PARAMETRO E TRANSFORMAR ESSES 4 EM APENAS 1

    @PatchMapping(path = "/aceitar/{id}")
    public void aceitarPedido(@PathVariable Long id) {
        pedidoService.alterarStatus(id, StatusPedidoEnum.EM_ANDAMENTO);
    }

    @PatchMapping(path = "/cancelar/{id}")
    public void cancelarPedido(@PathVariable Long id) {
        pedidoService.alterarStatus(id, StatusPedidoEnum.CANCELADO);
    }

    @PatchMapping(path = "/concluir/{id}")
    public void concluirPedido(@PathVariable Long id) {
        pedidoService.alterarStatus(id, StatusPedidoEnum.PRONTO);
    }

    @PatchMapping(path = "/finalizar/{id}")
    public void finalizarPedido(@PathVariable Long id) {
        pedidoService.alterarStatus(id, StatusPedidoEnum.FINALIZADO);
    }
}
