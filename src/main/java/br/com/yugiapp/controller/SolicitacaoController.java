package br.com.yugiapp.controller;

import br.com.yugiapp.dto.SolicitacaoFilterRequestDTO;
import br.com.yugiapp.dto.SolicitacaoRequestDTO;
import br.com.yugiapp.model.Solicitacao;
import br.com.yugiapp.service.SolicitacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitacao")
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public List<Solicitacao> getAll(SolicitacaoFilterRequestDTO solicitacaoFilterRequestDTO) {
        return solicitacaoService.getAllByFilters(solicitacaoFilterRequestDTO);
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String simpSessionId = Optional.ofNullable(headerAccessor.getSessionId()).orElseThrow();
        messagingTemplate.convertAndSend("/topic/session/" + simpSessionId, simpSessionId);
    }

    @MessageMapping("/solicitacao/solicitar")
    public void solicitarComanda(SolicitacaoRequestDTO solicitacaoRequestDTO, Message<String> message) {
        messagingTemplate.convertAndSend("/topic/solicitacoes", solicitacaoService.save(
                solicitacaoService.buildNovaSolicitacao(solicitacaoRequestDTO, (String) message.getHeaders().get("simpSessionId"))));
    }

    @MessageMapping("/solicitacao/aceitar")
    public void aceitarSolicitacao(Solicitacao solicitacaoRequest) {
        Solicitacao solicitacao = solicitacaoService.getById(solicitacaoRequest.getId());
        messagingTemplate.convertAndSend("/topic/comanda/" + solicitacao.getSessionId(), solicitacaoService.aceitarSolicitacao(solicitacao));
    }

    @MessageMapping("/solicitacao/recusar")
    public void recusarSolicitacao(Solicitacao solicitacaoRequest) {
        Solicitacao solicitacao = solicitacaoService.getById(solicitacaoRequest.getId());
        messagingTemplate.convertAndSend("/topic/comanda/" + solicitacao.getSessionId(), solicitacaoService.recusarSolicitacao((solicitacao)));
    }

}
