package br.com.yugiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {

    private String observacao;
    private String nomeProduto;
    private Double valorProduto;
    private Long quantidade;
}
