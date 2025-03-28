package br.com.yugiapp.specs;

import br.com.yugiapp.dto.PedidoFilterRequestDTO;
import br.com.yugiapp.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PedidoSpecifications extends DefaultSpecifications<Pedido, PedidoFilterRequestDTO> {

    public Specification<Pedido> getSpecs(PedidoFilterRequestDTO filter) {
        return Specification.where(buildSpecifiction(filter));
    }

    @Override
    public Specification<Pedido> buildSpecifiction(PedidoFilterRequestDTO filter) {
        List<Specification<Pedido>> specifications = Arrays.asList(
                getBasicSpecs(),
                wrapSpec(filter.getStatus(), v -> equals(v, "status")),
                wrapSpec(filter.getDataHora(), v -> greaterThanOrEqualTo(v, "dataHora")),
                wrapSpec(filter.getComanda(), v -> equals(v, "comanda.numero"))
        );
        return specifications.stream().filter(Objects::nonNull).reduce(Specification::and).orElse(null);
    }
}
