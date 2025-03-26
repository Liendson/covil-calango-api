package br.com.yugiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private String comanda;
    private String observacao;
    private ProdutoDTO produto;
    private Long quantidade;
}
