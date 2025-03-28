package br.com.yugiapp.specs;

import br.com.yugiapp.model.Pedido;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Classe abstrata que fornece métodos genéricos para criação de especificações (Specifications)
 * para entidades do tipo {@code T}, com base no filtro fornecido do tipo {@code F}.
 *
 * @param <T> O tipo da entidade.
 * @param <F> O tipo do filtro.
 */
public abstract class DefaultSpecifications<T, F> {

    /**
     * Retorna uma {@link Specification} para a entidade {@code T}, construída com base no filtro fornecido.
     *
     * @param filter O filtro a ser aplicado para construir a especificação.
     * @return A {@link Specification} gerada com base no filtro fornecido.
     */
    public Specification<T> getSpecs(F filter) {
        return Specification.where(buildSpecifiction(filter));
    }

    /**
     * Retorna uma especificação básica que sempre retorna uma condição verdadeira
     * (como se fosse um "AND" sem restrições).
     *
     * @return Uma {@link Specification} que sempre retorna uma condição verdadeira.
     */
    public Specification<T> getBasicSpecs() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    /**
     * Cria uma especificação condicionalmente com base no valor fornecido.
     * Se o valor for {@code null}, retorna {@code null}. Caso contrário, aplica a função
     * fornecida para gerar a especificação.
     *
     * @param <N> O tipo do valor.
     * @param value O valor a ser usado para gerar a especificação.
     * @param function A função que cria a especificação com base no valor.
     * @return A {@link Specification} gerada pela função, ou {@code null} se o valor for {@code null}.
     */
    public <N> Specification<T> wrapSpec(N value, Function<N, Specification<T>> function) {
        return Objects.nonNull(value) ? function.apply(value) : null;
    }

    /**
     * Método abstrato que deve ser implementado pelas subclasses para construir a especificação
     * com base no filtro fornecido.
     *
     * @param filter O filtro a ser usado para construir a especificação.
     * @return A {@link Specification} construída com base no filtro.
     */
    abstract Specification<T> buildSpecifiction(F filter);

    /**
     * Cria uma especificação que verifica se o valor de um atributo está presente em uma lista fornecida.
     *
     * @param list A lista de valores para comparar com o atributo.
     * @param attribute O nome do atributo a ser comparado (caso composto, adicione um ".").
     * @return A {@link Specification} que verifica se o atributo está presente na lista.
     */
    public Specification<T> equals(List<String> list, String attribute) {
        return (root, query, criteriaBuilder) -> {
            String[] parts = attribute.split("\\.");
            Path<T> path = root;
            for (String part : parts) {
                path = path.get(part);
            }
            return criteriaBuilder.and(path.in(list));
        };
    }

    /**
     * Cria uma {@link Specification} que verifica se o valor de um atributo {@code attribute}
     * é maior ou igual à data e hora de início do dia especificado pelo valor fornecido.
     *
     * Este método converte o valor {@code value} para {@link LocalDate},
     * define o início do dia (00:00) e verifica se o atributo fornecido é maior ou igual a essa data e hora.
     *
     * @param value O valor {@link LocalDateTime} a ser usado para comparar com o atributo.
     *              A data será extraída a partir do valor fornecido, e a hora será definida como 00:00 (início do dia).
     * @param attribute O nome do atributo da entidade a ser comparado com o valor de data e hora.
     * @return Uma {@link Specification} que verifica se o atributo é maior ou igual à data e hora do início do dia.
     */
    public Specification<T> greaterThanOrEqualTo(LocalDateTime value, String attribute) {
        return (root, query, criteriaBuilder) -> {
            LocalDate hoje = value.toLocalDate();
            LocalDateTime dataInicio = hoje.atStartOfDay();
            return criteriaBuilder.greaterThanOrEqualTo(root.get(attribute), dataInicio);
        };
    }

    /**
     * Cria uma especificação que verifica se o valor de um atributo é igual ao valor fornecido.
     *
     * @param value O valor a ser comparado com o atributo.
     * @param attribute O nome do atributo a ser comparado.
     * @return A {@link Specification} que verifica se o atributo é igual ao valor fornecido.
     */
    public Specification<T> equals(String value, String attribute) {
        return equals(Collections.singletonList(value), attribute);
    }
}
