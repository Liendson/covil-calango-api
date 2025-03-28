package br.com.yugiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {

    private String comanda;
    private String observacao;
    private ProdutoRequestDTO produto;
    private Long quantidade;
}
