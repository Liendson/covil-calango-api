package br.com.yugiapp.service;

import br.com.yugiapp.Constantes;
import br.com.yugiapp.converter.ComandaConverter;
import br.com.yugiapp.dto.ComandaFilterRequestDTO;
import br.com.yugiapp.dto.ComandaResponseDTO;
import br.com.yugiapp.dto.PedidoFilterRequestDTO;
import br.com.yugiapp.enums.StatusComandaEnum;
import br.com.yugiapp.enums.StatusPedidoEnum;
import br.com.yugiapp.model.Comanda;
import br.com.yugiapp.model.Pedido;
import br.com.yugiapp.model.Usuario;
import br.com.yugiapp.repository.ComandaRepository;
import br.com.yugiapp.specs.ComandaSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Lazy })
public class ComandaService {

    private final ComandaRepository comandaRepository;
    private final ComandaSpecifications comandaSpecifications;
    private final ComandaConverter comandaConverter;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;
    private final LocalDateTime HOJE = LocalDateTime.now();

    public List<ComandaResponseDTO> getAllByFilters(ComandaFilterRequestDTO pedidoFilterRequestDTO) {
        return comandaRepository.findAll(comandaSpecifications.getSpecs(pedidoFilterRequestDTO))
                .stream()
                .map(comandaConverter::toResponseDTO)
                .toList();
    }

    public Comanda getByNumero(String numero) {
        return comandaRepository.findByNumero(numero).orElseThrow();
    }

    public ComandaResponseDTO getResponseDTOByNumero(String numero) {
        Comanda comanda = comandaRepository.findByNumero(numero).orElseThrow();
        ComandaResponseDTO comandaResponseDTO = comandaConverter.toResponseDTO(comanda);
        comandaResponseDTO.setPedidos(pedidoService.getAllResponseDTOByFilters(PedidoFilterRequestDTO.builder().comanda(numero).build()));
        comandaResponseDTO.setValorTotal(obterValorTotalDaComanda(comanda, true));
        return comandaResponseDTO;
    }

    public ComandaResponseDTO gerarComandaDTO(String nome) {
        Usuario usuario = Usuario.builder().id(1L).nome(nome).build();
        Comanda comandaCompleta = gerarComanda(usuario);
        return ComandaResponseDTO.builder()
                .numero(comandaCompleta.getNumero())
                .dataHoraEntrada(comandaCompleta.getDataHoraEntrada())
                .usuario(usuarioService.criarUsuarioConvidado(usuario.getNome()))
                .build();
    }

    public Comanda gerarComanda(Usuario usuario) {
        Comanda comandaSemNumero = comandaRepository.save(
                Comanda.builder()
                        .dataHoraEntrada(LocalDateTime.now())
                        .usuario(usuario)
                        .status(StatusComandaEnum.EM_ANALISE.getValue())
                        .build());
        comandaSemNumero.setNumero(String.valueOf(HOJE.getYear()) + HOJE.getMonthValue() + HOJE.getDayOfMonth() + comandaSemNumero.getId());
        return comandaRepository.save(comandaSemNumero);
    }

    public Comanda alterarStatus(String numero, StatusComandaEnum statusComandaEnum) {
        Comanda comandaAlterada = comandaRepository.findByNumero(numero).orElseThrow();
        if (StatusComandaEnum.FECHADA.getValue().equals(statusComandaEnum.getValue())) {
            comandaAlterada.setDataHoraSaida(HOJE);
        }
        if (StatusComandaEnum.ABERTA.getValue().equals(statusComandaEnum.getValue())) {
            comandaAlterada.setDataHoraEntrada(HOJE);
        }
        comandaAlterada.setStatus(statusComandaEnum.getValue());
        return save(comandaAlterada);
    }

    public Double obterValorTotalDaComanda(Comanda comanda, Boolean temPassaporte) {
        PedidoFilterRequestDTO filtros = PedidoFilterRequestDTO.builder().comanda(comanda.getNumero()).status(
                List.of(StatusPedidoEnum.SOLICITADO.name(), StatusPedidoEnum.EM_ANDAMENTO.name())).build();
        List<Pedido> pedidosDaComanda = pedidoService.getAllByFilters(filtros);
        Double valorTotal = pedidosDaComanda.stream().map(p -> (p.getProduto().getValor()) * p.getQuantidade()).reduce(0.0, Double::sum);
        return temPassaporte ? valorTotal + Constantes.VALOR_PASSAPORTE : valorTotal;
    }

    public Comanda save(Comanda jogador) {
        return comandaRepository.save(jogador);
    }

}
