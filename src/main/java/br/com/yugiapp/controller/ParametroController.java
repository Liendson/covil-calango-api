package br.com.yugiapp.controller;

import br.com.yugiapp.dto.ParametroResponseDTO;
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
    public ParametroResponseDTO findByNome(@PathVariable String nome) {
        return parametroService.findByNome(nome).map(p ->
                ParametroResponseDTO.builder()
                        .nome(p.getNome())
                        .descricao(p.getDescricao())
                        .build())
                .orElseThrow();
    }
}
