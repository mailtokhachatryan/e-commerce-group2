package com.smartcode.ecommerce.spec;

import com.smartcode.ecommerce.model.dto.filter.UserFilterModel;
import com.smartcode.ecommerce.model.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

import static java.util.Objects.nonNull;

@Service
public class UserSpecification {

    private Specification<UserEntity> search(UserFilterModel userFilterModel) {
        return Specification.where((root, criteriaQuery, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            UserFilterModel.Search search = userFilterModel.getSearch();
            if (nonNull(search.getText())) {
                Predicate nameLike = criteriaBuilder.like(root.get("name"), "%" + search.getText() + "%");
                predicates.add(nameLike);

                Predicate lastNameLike = criteriaBuilder.like(root.get("lastname"), "%" + search.getText() + "%");
                predicates.add(lastNameLike);

                Predicate emailLike = criteriaBuilder.like(root.get("email"), "%" + search.getText() + "%");
                predicates.add(emailLike);
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        });
    }

    private Specification<UserEntity> filter(UserFilterModel userFilterModel) {
        return Specification.where((root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();
            UserFilterModel.Filter filter = userFilterModel.getFilter();

            if (filter.getStartAge() != null) {
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("age"), filter.getStartAge());
                predicates.add(predicate);
            }

            if (filter.getEndAge() != null) {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("age"), filter.getEndAge());
                predicates.add(predicate);
            }

            if (filter.getIsVerified() != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("isVerified"), filter.getIsVerified());
                predicates.add(predicate);
            }

            if (nonNull(userFilterModel.getSearch().getText())) {
                Predicate nameLike = criteriaBuilder.like(root.get("name"), "%" + search.getText() + "%");
                predicates.add(nameLike);

                Predicate lastNameLike = criteriaBuilder.like(root.get("lastname"), "%" + search.getText() + "%");
                predicates.add(lastNameLike);

                Predicate emailLike = criteriaBuilder.like(root.get("email"), "%" + search.getText() + "%");
                predicates.add(emailLike);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });
    }

}
