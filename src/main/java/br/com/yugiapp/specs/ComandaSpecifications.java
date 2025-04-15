package br.com.yugiapp.specs;

import br.com.yugiapp.dto.ComandaFilterRequestDTO;
import br.com.yugiapp.model.Comanda;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ComandaSpecifications extends DefaultSpecifications<Comanda, ComandaFilterRequestDTO> {

    public Specification<Comanda> getSpecs(ComandaFilterRequestDTO filter) {
        return Specification.where(buildSpecifiction(filter));
    }

    @Override
    public Specification<Comanda> buildSpecifiction(ComandaFilterRequestDTO filter) {
        List<Specification<Comanda>> specifications = Arrays.asList(
                getBasicSpecs(),
                wrapSpec(filter.getStatus(), v -> equals(v, "status")),
                wrapSpec(filter.getDataHoraEntrada(), v -> greaterThanOrEqualTo(v, "dataHoraEntrada"))
        );
        return specifications.stream().filter(Objects::nonNull).reduce(Specification::and).orElse(null);
    }
}

