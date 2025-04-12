package br.com.yugiapp.controller;

import br.com.yugiapp.dto.SolicitacaoFilterRequestDTO;
import br.com.yugiapp.model.Solicitacao;
import br.com.yugiapp.service.SolicitacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacao")
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    @GetMapping
    public List<Solicitacao> getAll(SolicitacaoFilterRequestDTO solicitacaoFilterRequestDTO) {
        return solicitacaoService.getAllByFilters(solicitacaoFilterRequestDTO);
    }

}
