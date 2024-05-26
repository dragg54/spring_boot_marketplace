package ecommerce.specifications;

import ecommerce.dtos.queries.ProductSearchQuery;
import ecommerce.entities.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filterBySearchQuery(ProductSearchQuery query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (query.getProductName() != null && !query.getProductName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")),
                        "%" + query.getProductName().toLowerCase() + "%"));
            }
            if (query.getProductName() != null && query.getProductCategoryId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("productCategory").get("productCategoryId"),
                        query.getProductCategoryId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
