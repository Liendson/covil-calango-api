package br.com.yugiapp.converter;

import br.com.yugiapp.dto.PedidoResponseDTO;
import br.com.yugiapp.model.Pedido;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PedidoConverter {

    public PedidoResponseDTO toResponseDTO(Pedido entity) {
        return PedidoResponseDTO.builder()
                .nomeProduto(entity.getProduto().getNome())
                .quantidade(entity.getQuantidade())
                .observacao(entity.getObservacao())
                .valorProduto(entity.getProduto().getValor())
                .build();
    }

    public List<PedidoResponseDTO> toResponseDTO(List<Pedido> list) {
        return list.stream().map(this::toResponseDTO).toList();
    }
}
