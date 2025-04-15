package br.com.yugiapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusSolicitacaoEnum {

    EM_ANALISE("Em Análise"),
    ACEITA("Aceita"),
    RECUSADA("Recusada");;

    private final String value;

    public static StatusSolicitacaoEnum fromValue(String value) {
        for (StatusSolicitacaoEnum s : StatusSolicitacaoEnum.values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para o StatusSolicitacaoEnum: " + value);
    }

}
