package br.com.yugiapp.controller;

import br.com.yugiapp.dto.ParametroDTO;
import br.com.yugiapp.service.ParametroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parametros")
@RequiredArgsConstructor
public class ParametroController {

    private final ParametroService parametroService;

    @GetMapping(path = "/{nome}")
    public ParametroDTO findByNome(@PathVariable String nome) {
        return parametroService.findByNome(nome).map(p ->
                ParametroDTO.builder()
                        .nome(p.getNome())
                        .descricao(p.getDescricao())
                        .build())
                .orElseThrow();
    }
}
