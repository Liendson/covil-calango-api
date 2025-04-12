package br.com.yugiapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "TB_SOLICITACAO")
public class Solicitacao {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SESSIONID")
    private String sessionId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "FK_COMANDA")
    private Comanda comanda;
}
