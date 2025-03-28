package br.com.yugiapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PerfilEnum {
    ROLE_CLIENTE("Cliente"),
    ROLE_ADMINISTRATOR("Administrador");

    private final String value;

    public static PerfilEnum fromValue(String value) {
        for (PerfilEnum s : PerfilEnum.values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para o PerfilEnum: " + value);
    }
}
