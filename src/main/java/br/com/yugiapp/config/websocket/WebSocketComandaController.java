package br.com.yugiapp.config.websocket;

import br.com.yugiapp.enums.StatusComandaEnum;
import br.com.yugiapp.enums.StatusSolicitacaoEnum;
import br.com.yugiapp.model.Solicitacao;
import br.com.yugiapp.service.ComandaService;
import br.com.yugiapp.service.SolicitacaoService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class WebSocketComandaController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ComandaService comandaService;
    private final SolicitacaoService solicitacaoService;

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String simpSessionId = Optional.ofNullable(headerAccessor.getSessionId()).orElseThrow();
        messagingTemplate.convertAndSend("/topic/session/" + simpSessionId, simpSessionId);
    }

    @MessageMapping("/comanda/solicitar")
    public void solicitarComanda(String nome, Message<?> message) {
        String sessionId = (String) message.getHeaders().get("simpSessionId");
        if (solicitacaoService.findBySessionId(sessionId).isPresent()) {
            throw new IllegalArgumentException();
        }
        Solicitacao solicitacao = solicitacaoService.save(solicitacaoService.buildNovaSolicitacao(nome, sessionId));
        messagingTemplate.convertAndSend("/topic/solicitacoes", solicitacao);
    }

    @MessageMapping("/comanda/aceitar")
    public void aceitarSolicitacao(Solicitacao solicitacaoRequest) {
        Solicitacao solicitacao = solicitacaoService.getById(solicitacaoRequest.getId());
        String topico = "/topic/comanda/" + solicitacao.getSessionId();
        if (StatusComandaEnum.EM_ANALISE.getValue().equals(solicitacao.getComanda().getStatus())) {
            comandaService.alterarStatus(solicitacao.getComanda().getNumero(), StatusComandaEnum.ABERTA);
        }
        messagingTemplate.convertAndSend(topico, solicitacaoService.alterarStatus(solicitacao, StatusSolicitacaoEnum.ACEITA));
    }

    @MessageMapping("/comanda/recusar")
    public void recusarSolicitacao(Solicitacao solicitacaoRequest) {
        Solicitacao solicitacao = solicitacaoService.getById(solicitacaoRequest.getId());
        String topico = "/topic/comanda/" + solicitacao.getSessionId();
        comandaService.alterarStatus(solicitacao.getComanda().getNumero(), StatusComandaEnum.FECHADA);
        if (!StatusSolicitacaoEnum.EM_ANALISE.getValue().equals(solicitacao.getStatus())) {
            solicitacaoService.deleteById(solicitacao.getId());
        }
        messagingTemplate.convertAndSend(topico, solicitacaoService.alterarStatus(solicitacao, StatusSolicitacaoEnum.RECUSADA));
    }
}