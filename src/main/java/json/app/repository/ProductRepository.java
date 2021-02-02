package json.app.repository;

import json.app.domain.entity.Product;
import json.app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Set<Product> findAllByPriceBetweenAndBuyerOrderByPrice(BigDecimal more, BigDecimal less, User buyer);
}
