package br.com.yugiapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ComandaDTO implements Serializable {

    private String numero;

    private LocalDateTime dataHoraEntrada;

    private LocalDateTime dataHoraSaida;

    private Double valorTotal;

}
