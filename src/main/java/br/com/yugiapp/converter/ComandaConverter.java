package br.com.yugiapp.converter;

import br.com.yugiapp.dto.ComandaResponseDTO;
import br.com.yugiapp.dto.UsuarioConvidadoResponseDTO;
import br.com.yugiapp.model.Comanda;
import br.com.yugiapp.service.ComandaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ComandaConverter {

    public ComandaService comandaService;

    public ComandaResponseDTO toResponseDTO(Comanda entity) {
        return ComandaResponseDTO.builder()
                .usuario(UsuarioConvidadoResponseDTO.builder().nome(entity.getUsuario().getNome()).build())
                .numero(entity.getNumero())
                .dataHoraEntrada(entity.getDataHoraEntrada())
                .dataHoraSaida(entity.getDataHoraSaida())
                .valorTotal(comandaService.obterValorTotalDaComanda(entity, true)).build();
    }
}
