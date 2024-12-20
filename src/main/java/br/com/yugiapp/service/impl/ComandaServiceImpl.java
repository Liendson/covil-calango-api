package br.com.yugiapp.service.impl;

import br.com.yugiapp.model.Comanda;
import br.com.yugiapp.repository.ComandaRepository;
import br.com.yugiapp.service.ComandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ComandaServiceImpl implements ComandaService {

    private final ComandaRepository comandaRepository;

    @Override
    public String gerarNumeroDaComanda() {
        LocalDateTime dataHoje = LocalDateTime.now();
        Comanda novaComanda = comandaRepository.save(Comanda.builder().dataHoraEntrada(LocalDateTime.now()).build());
        novaComanda.setNumero(String.valueOf(dataHoje.getYear() + dataHoje.getMonthValue() + dataHoje.getDayOfMonth() + novaComanda.getId()));
        return novaComanda.getNumero();
    }

    @Override
    public void fecharComanda(String numeroComanda) {
        Comanda comanda = comandaRepository.findByNumero(numeroComanda);
        comanda.setDataHoraSaida(LocalDateTime.now());
    }
}
