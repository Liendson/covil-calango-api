package br.com.yugiapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RankingEnum {

    UNRANKED("Sem Ranking"),
    BRONZE("Bronze"),
    PRATA("Prata"),
    OURO("Ouro"),
    PLATINA("Platina"),
    DIAMANTE("Diamante");
    // TODO: Adicionar mais rankings

    private final String value;

    public static RankingEnum fromValue(String value) {
        for (RankingEnum s : RankingEnum.values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para o RankingEnum: " + value);
    }
}
