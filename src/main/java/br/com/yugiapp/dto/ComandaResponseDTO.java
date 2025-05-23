package br.com.yugiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ComandaResponseDTO implements Serializable {

    private String numero;

    private UsuarioConvidadoResponseDTO usuario;

    private LocalDateTime dataHoraEntrada;

    private LocalDateTime dataHoraSaida;

    private Double valorTotal;

    private String status;

    private List<PedidoResponseDTO> pedidos;

}
