package br.com.yugiapp.model;

import br.com.yugiapp.enums.TierTorneioEnum;
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
@Table(name = "TB_TORNEIO")
public class Torneio implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIER")
    @Enumerated(EnumType.ORDINAL)
    private TierTorneioEnum tier;

    @Column(name = "PREMIACAO")
    private String premiacao;

    @Column(name = "VALOR_INSCRICAO")
    private Long valorInscricao;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataHora;

}
