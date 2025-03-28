package br.com.yugiapp.specs;

import br.com.yugiapp.dto.TorneioFilterRequestDTO;
import br.com.yugiapp.model.Torneio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TorneioSpecifications extends DefaultSpecifications<Torneio, TorneioFilterRequestDTO> {

    public Specification<Torneio> getSpecs(TorneioFilterRequestDTO filter) {
        return Specification.where(buildSpecifiction(filter));
    }

    @Override
    public Specification<Torneio> buildSpecifiction(TorneioFilterRequestDTO filter) {
        List<Specification<Torneio>> specifications = Arrays.asList(
                getBasicSpecs(),
                wrapSpec(filter.getNome(), v -> equals(v, "nome")),
                wrapSpec(filter.getTier(), v -> equals(v, "tier")),
                wrapSpec(filter.getDataHora(), v -> greaterThanOrEqualTo(v, "dataHora"))
        );
        return specifications.stream().filter(Objects::nonNull).reduce(Specification::and).orElse(null);
    }
}
