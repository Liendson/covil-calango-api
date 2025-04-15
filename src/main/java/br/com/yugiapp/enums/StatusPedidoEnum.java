package br.com.yugiapp.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusPedidoEnum {

    SOLICITADO("Solicitado"),
    EM_ANDAMENTO("Em Andamento"),
    PRONTO("Pronto"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private final String value;

    public static StatusPedidoEnum fromValue(String value) {
        for (StatusPedidoEnum s : StatusPedidoEnum.values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para o StatusPedidoEnum: " + value);
    }

}
