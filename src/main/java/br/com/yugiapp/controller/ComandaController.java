package br.com.yugiapp.controller;

import br.com.yugiapp.dto.ComandaFilterRequestDTO;
import br.com.yugiapp.dto.ComandaResponseDTO;
import br.com.yugiapp.enums.StatusComandaEnum;
import br.com.yugiapp.model.Comanda;
import br.com.yugiapp.service.ComandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comanda")
@RequiredArgsConstructor
public class ComandaController {

    private final ComandaService comandaService;

    @GetMapping
    public List<ComandaResponseDTO> getByFilters(ComandaFilterRequestDTO comandaFilterRequestDTO) {
        return comandaService.getAllByFilters(comandaFilterRequestDTO);
    }

    @GetMapping(path = "/gerar/{nome}")
    public ComandaResponseDTO gerarComandaEntrada(@PathVariable String nome) {
        return comandaService.gerarComandaDTO(nome);
    }

    @PutMapping(path = "/fechar/{numero}")
    public void fecharComanda(@PathVariable String numero) {
        comandaService.alterarStatus(numero, StatusComandaEnum.FECHADA);
    }

    @GetMapping(path = "{numero}")
    public Comanda getByNumero(@PathVariable String numero) {
        return comandaService.getByNumero(numero);
    }

}
