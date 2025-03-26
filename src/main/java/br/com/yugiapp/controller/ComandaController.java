package br.com.yugiapp.controller;

import br.com.yugiapp.dto.ComandaDTO;
import br.com.yugiapp.service.ComandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comanda")
@RequiredArgsConstructor
public class ComandaController {

    private final ComandaService comandaService;

    @GetMapping(path = "/gerar/{nome}")
    public ComandaDTO gerarComandaEntrada(@PathVariable String nome) {
        return comandaService.gerarNumeroDaComanda(nome);
    }

    @PostMapping(path = "/fechar/{numero}")
    public void fecharComanda(@PathVariable String numero) {
        comandaService.fecharComanda(numero);
    }
}
