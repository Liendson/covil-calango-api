package br.com.yugiapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TierTorneioEnum {

    UM(1L),
    DOIS(2L),
    TRES(3L);

    private final Long value;

    public static TierTorneioEnum fromValue(Long value) {
        for (TierTorneioEnum s : TierTorneioEnum.values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para o StatusPedidoEnum: " + value);
    }
}
