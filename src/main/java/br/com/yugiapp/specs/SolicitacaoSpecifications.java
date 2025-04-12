package br.com.yugiapp.specs;

import br.com.yugiapp.dto.SolicitacaoFilterRequestDTO;
import br.com.yugiapp.model.Solicitacao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SolicitacaoSpecifications extends DefaultSpecifications<Solicitacao, SolicitacaoFilterRequestDTO> {

    public Specification<Solicitacao> getSpecs(SolicitacaoFilterRequestDTO filter) {
        return Specification.where(buildSpecifiction(filter));
    }

    @Override
    public Specification<Solicitacao> buildSpecifiction(SolicitacaoFilterRequestDTO filter) {
        List<Specification<Solicitacao>> specifications = Arrays.asList(
                getBasicSpecs(),
                wrapSpec(filter.getStatus(), v -> equals(v, "status")),
                wrapSpec(filter.getSessionId(), v -> equals(v, "sessionId"))
        );
        return specifications.stream().filter(Objects::nonNull).reduce(Specification::and).orElse(null);
    }
}

