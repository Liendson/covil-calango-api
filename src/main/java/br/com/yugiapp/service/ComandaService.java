package br.com.yugiapp.service;

import br.com.yugiapp.dto.ComandaDTO;
import br.com.yugiapp.enums.StatusComandaEnum;
import br.com.yugiapp.model.Comanda;
import br.com.yugiapp.model.Usuario;
import br.com.yugiapp.repository.ComandaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ComandaService {

    private final ComandaRepository comandaRepository;
    private final UsuarioService usuarioService;

    public Comanda getByNumero(String numero) {
        return comandaRepository.findByNumero(numero).orElseThrow();
    }

    public ComandaDTO gerarNumeroDaComanda(String nome) {
        LocalDateTime dataHoje = LocalDateTime.now();
        Usuario usuario = Usuario.builder().id(1L).nome(nome).build();
        Comanda comandaSemNumero = comandaRepository.save(
                Comanda.builder()
                        .dataHoraEntrada(LocalDateTime.now())
                        .usuario(usuario)
                        .status(StatusComandaEnum.ABERTA.getValue())
                        .build());
        comandaSemNumero.setNumero(String.valueOf(dataHoje.getYear()) + dataHoje.getMonthValue() + dataHoje.getDayOfMonth() + comandaSemNumero.getId());
        Comanda comandaCompleta = comandaRepository.save(comandaSemNumero);
        return ComandaDTO.builder()
                .numero(comandaCompleta.getNumero())
                .dataHoraEntrada(comandaCompleta.getDataHoraEntrada())
                .usuario(usuarioService.criarUsuarioConvidado(usuario.getNome()))
                .build();
    }

    public void fecharComanda(String numero) {
        Comanda comanda = comandaRepository.findByNumero(numero).orElseThrow();
        comanda.setDataHoraSaida(LocalDateTime.now());
        comanda.setStatus(StatusComandaEnum.FECHADA.getValue());
    }
}
