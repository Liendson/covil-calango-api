package br.com.yugiapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "TB_LIGA")
public class Liga implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDate dtAtualizacao;

    @ManyToOne
    @JoinColumn(name = "FK_CAMPEAO")
    private Jogador campeao;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_participantes",
            joinColumns = @JoinColumn(name = "id_liga"),
            inverseJoinColumns = @JoinColumn(name = "id_jogador"))
    private List<Jogador> participantes;

    @Column(name = "PREMIACAO")
    private String premiacao;

    @Column(name = "VIGENTE")
    private Boolean vigente;
}
