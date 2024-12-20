package br.com.yugiapp.model;

import br.com.yugiapp.enums.RankingJogadorEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "TB_JOGADOR")
public class Jogador implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PONTOS")
    private Long pontos;

    @Column(name = "RANKING")
    @Enumerated(EnumType.STRING)
    private RankingJogadorEnum ranking;

    @Column(name = "CREDITOS")
    private Double creditos;

    @Column(name = "TCGID")
    private String tcgId;

    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;

}
