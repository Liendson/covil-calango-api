package br.com.yugiapp.model;

import br.com.yugiapp.enums.StatusPedidoEnum;
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
@Table(name = "TB_PEDIDO")
public class Pedido implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataHora;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUTO")
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Long quantidade;
}
