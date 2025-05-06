package br.com.yugiapp.service;

import br.com.yugiapp.dto.SolicitacaoFilterRequestDTO;
import br.com.yugiapp.dto.SolicitacaoRequestDTO;
import br.com.yugiapp.enums.StatusComandaEnum;
import br.com.yugiapp.enums.StatusSolicitacaoEnum;
import br.com.yugiapp.model.Comanda;
import br.com.yugiapp.model.Solicitacao;
import br.com.yugiapp.model.Usuario;
import br.com.yugiapp.repository.SolicitacaoRepository;
import br.com.yugiapp.specs.SolicitacaoSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Solicitacao buildNovaSolicitacao(SolicitacaoRequestDTO solicitacaoRequestDTO, String sessionId) {
        return Solicitacao.builder()
                .nome(solicitacaoRequestDTO.getUsuario().replaceAll("\"", ""))
                .email(solicitacaoRequestDTO.getEmail())
                .sessionId(sessionId)
                .dataHora(LocalDateTime.now())
                .status(StatusSolicitacaoEnum.EM_ANALISE.getValue())
                .build();
    }

    public Solicitacao save(Solicitacao solicitacao) {
        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao alterarStatus(Solicitacao solicitacao, StatusSolicitacaoEnum statusSolicitacaoEnum) {
        solicitacao.setStatus(statusSolicitacaoEnum.getValue());
        return save(solicitacao);
    }

    public Solicitacao aceitarSolicitacao(Solicitacao solicitacao) {
        // TODO: reavaliar criação do usuário ao aceitar solicitacao, lógica não está fazendo sentido pois qualquer um consegue acessar via email
        Optional<Usuario> usuarioSolicitacao = usuarioService.getByEmail(solicitacao.getEmail());
        Comanda comandaVisitante = comandaService.gerarComanda(usuarioSolicitacao.orElseGet(() -> usuarioService.criarNovoUsuario(solicitacao)));
        Solicitacao solicitacaoAceita = alterarStatus(solicitacao, StatusSolicitacaoEnum.ACEITA);
        solicitacaoAceita.setComanda(comandaService.alterarStatus(comandaVisitante.getNumero(), StatusComandaEnum.ABERTA));
        return solicitacaoAceita;
    }

    public Solicitacao recusarSolicitacao(Solicitacao solicitacao) {
        if (!StatusSolicitacaoEnum.EM_ANALISE.getValue().equals(solicitacao.getStatus())) {
            deleteById(solicitacao.getId());
        }
        return alterarStatus(solicitacao, StatusSolicitacaoEnum.RECUSADA);
    }

}
