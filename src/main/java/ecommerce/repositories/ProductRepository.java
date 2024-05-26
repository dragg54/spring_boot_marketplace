package ecommerce.repositories;

import ecommerce.dtos.queries.ProductSearchQuery;
import ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ecommerce.dtos.responses.ProductResponse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor {

}
