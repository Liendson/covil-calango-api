package br.com.yugiapp.controller;

import br.com.yugiapp.dto.ComandaResponseDTO;
import br.com.yugiapp.service.ComandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comanda")
@RequiredArgsConstructor
public class ComandaController {

    private final ComandaService comandaService;

    @GetMapping(path = "/gerar/{nome}")
    public ComandaResponseDTO gerarComandaEntrada(@PathVariable String nome) {
        return comandaService.gerarNumeroDaComanda(nome);
    }

    @PutMapping(path = "/fechar/{numero}")
    public void fecharComanda(@PathVariable String numero) {
        comandaService.fecharComanda(numero);
    }
}
