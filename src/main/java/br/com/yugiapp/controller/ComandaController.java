package br.com.yugiapp.controller;

import br.com.yugiapp.service.ComandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comanda")
@RequiredArgsConstructor
public class ComandaController {

    private final ComandaService comandaService;

    @GetMapping
    public String gerarComandaEntrada() {
        return comandaService.gerarNumeroDaComanda();
    }

    @PostMapping(path = "/fechar/{numero}")
    public void fecharComanda(@PathVariable String numero) {
        comandaService.fecharComanda(numero);
    }
}
