package br.com.yugiapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "TB_COMANDA")
public class Comanda implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "DT_HORA_ENTRADA")
    private LocalDateTime dataHoraEntrada;

    @Column(name = "DT_HORA_SAIDA")
    private LocalDateTime dataHoraSaida;

}
