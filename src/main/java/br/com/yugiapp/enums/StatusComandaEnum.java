package br.com.yugiapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusComandaEnum {

    ABERTA("Aberta"),
    FECHADA("Fechada");

    private final String value;

    public static StatusComandaEnum fromValue(String value) {
        for (StatusComandaEnum s : StatusComandaEnum.values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para o StatusComandaEnum: " + value);
    }

}
