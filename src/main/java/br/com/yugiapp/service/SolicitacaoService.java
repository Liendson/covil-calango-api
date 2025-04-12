package br.com.yugiapp.service;

import br.com.yugiapp.dto.SolicitacaoFilterRequestDTO;
import br.com.yugiapp.enums.StatusSolicitacaoEnum;
import br.com.yugiapp.model.Comanda;
import br.com.yugiapp.model.Solicitacao;
import br.com.yugiapp.repository.SolicitacaoRepository;
import br.com.yugiapp.specs.SolicitacaoSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final ComandaService comandaService;
    private final UsuarioService usuarioService;

    private final SolicitacaoSpecifications solicitacaoSpecifications;

    public List<Solicitacao> getAll() {
        return solicitacaoRepository.findAll();
    }

    public List<Solicitacao> getAllByFilters(SolicitacaoFilterRequestDTO solicitacaoFilterRequestDTO) {
        return solicitacaoRepository.findAll(solicitacaoSpecifications.buildSpecifiction(solicitacaoFilterRequestDTO));
    }

    public Optional<Solicitacao> findBySessionId(String id) {
        return solicitacaoRepository.findBySessionId(id);
    }

    public Solicitacao getById(Long id) {
        return solicitacaoRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        solicitacaoRepository.deleteById(id);
    }

    public Solicitacao buildNovaSolicitacao(String nome, String sessionId) {
        Comanda comandaVisitante = comandaService.gerarComanda(usuarioService.obterUsuarioVisitante());
        return Solicitacao.builder()
                .nome(nome)
                .sessionId(sessionId)
                .comanda(comandaVisitante)
                .status(StatusSolicitacaoEnum.EM_ANALISE.getValue())
                .build();
    }

    public Solicitacao save(Solicitacao solicitacao) {
        return solicitacaoRepository.save(solicitacao);
    }

    public void alterarStatus(Solicitacao solicitacao, StatusSolicitacaoEnum statusSolicitacaoEnum) {
        Solicitacao solicitacaoAlterada = solicitacaoRepository.findBySessionId(solicitacao.getSessionId()).orElseThrow();
        solicitacaoAlterada.setStatus(statusSolicitacaoEnum.getValue());
        save(solicitacaoAlterada);
    }

}
