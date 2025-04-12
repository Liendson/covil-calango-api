package br.com.yugiapp.service;

import br.com.yugiapp.dto.ComandaResponseDTO;
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
    private final LocalDateTime HOJE = LocalDateTime.now();

    public Comanda getByNumero(String numero) {
        return comandaRepository.findByNumero(numero).orElseThrow();
    }

    public ComandaResponseDTO gerarComandaDTO(String nome) {
        Usuario usuario = Usuario.builder().id(1L).nome(nome).build();
        Comanda comandaCompleta = gerarComanda(usuario);
        return ComandaResponseDTO.builder()
                .numero(comandaCompleta.getNumero())
                .dataHoraEntrada(comandaCompleta.getDataHoraEntrada())
                .usuario(usuarioService.criarUsuarioConvidado(usuario.getNome()))
                .build();
    }

    public Comanda gerarComanda(Usuario usuario) {
        Comanda comandaSemNumero = comandaRepository.save(
                Comanda.builder()
                        .dataHoraEntrada(LocalDateTime.now())
                        .usuario(usuario)
                        .status(StatusComandaEnum.EM_ANALISE.getValue())
                        .build());
        comandaSemNumero.setNumero(String.valueOf(HOJE.getYear()) + HOJE.getMonthValue() + HOJE.getDayOfMonth() + comandaSemNumero.getId());
        return comandaRepository.save(comandaSemNumero);
    }

    public void alterarStatus(String numero, StatusComandaEnum statusComandaEnum) {
        Comanda comandaAlterada = comandaRepository.findByNumero(numero).orElseThrow();
        if (StatusComandaEnum.FECHADA.getValue().equals(statusComandaEnum.getValue())) {
            comandaAlterada.setDataHoraSaida(HOJE);
        }
        if (StatusComandaEnum.ABERTA.getValue().equals(statusComandaEnum.getValue())) {
            comandaAlterada.setDataHoraEntrada(HOJE);
        }
        comandaAlterada.setStatus(statusComandaEnum.getValue());
        save(comandaAlterada);
    }

    public Comanda save(Comanda jogador) {
        return comandaRepository.save(jogador);
    }

}
