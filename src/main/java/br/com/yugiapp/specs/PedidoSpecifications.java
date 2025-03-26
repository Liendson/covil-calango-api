package br.com.yugiapp.specs;

import br.com.yugiapp.dto.PedidoFilterDTO;
import br.com.yugiapp.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PedidoSpecifications {

    public Specification<Pedido> getSpecs(PedidoFilterDTO pedidoFilterDTO) {
        return Specification.where(buildSpecifiction(pedidoFilterDTO));
    }

    public Specification<Pedido> buildSpecifiction(PedidoFilterDTO pedidoFilterDTO) {
        List<Specification<Pedido>> specifications = Arrays.asList(
                getBasicSpecs(),
                wrapSpec(pedidoFilterDTO.getStatus(), this::equalStatus),
                wrapSpec(pedidoFilterDTO.getDataHora(), this::equalDataHora),
                wrapSpec(pedidoFilterDTO.getComanda(), this::equalComanda)
        );
        return specifications.stream().filter(Objects::nonNull).reduce(Specification::and).orElse(null);
    }

    private <T> Specification<Pedido> wrapSpec(T value, Function<T, Specification<Pedido>> function) {
        return Objects.nonNull(value) ? function.apply(value) : null;
    }

    private Specification<Pedido> getBasicSpecs() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    private Specification<Pedido> equalStatus(List<String> status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(root.get("status").in(status));
    }

    private Specification<Pedido> equalComanda(String comanda) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("comanda").get("numero"), comanda);
    }


    private Specification<Pedido> equalDataHora(LocalDateTime dataHora) {
        return (root, query, criteriaBuilder) -> {
            LocalDate hoje = dataHora.toLocalDate();
            LocalDateTime dataInicio = hoje.atStartOfDay();
            return criteriaBuilder.greaterThanOrEqualTo(root.get("dataHora"), dataInicio);
        };
    }
}
