package br.com.yugiapp.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RankingJogadorEnum {

    UNRANKED("Sem Ranking"),
    BRONZE("Bronze"),
    PRATA("Prata"),
    OURO("Ouro"),
    PLATINA("Platina"),
    DIAMANTE("Diamante");
    // TODO: Adicionar mais rankings

    private final String value;

    public static RankingJogadorEnum fromValue(String value) {
        for (RankingJogadorEnum s : RankingJogadorEnum.values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para o RankingJogadorEnum: " + value);
    }
}
