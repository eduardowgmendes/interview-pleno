package br.com.brainweb.interview.core.features.filter;

import br.com.brainweb.interview.domain.Hero;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class HeroSpecification implements Specification<Hero> {

    private final Hero filter;

    public HeroSpecification(Hero filter) {
        this.filter = filter;
    }


    @Override
    public Predicate toPredicate(Root<Hero> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (Objects.nonNull(this.filter.getName()) && Strings.isNotEmpty(this.filter.getName())) {
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("name"), this.filter.getName()));
        }

        return predicate;
    }
}
