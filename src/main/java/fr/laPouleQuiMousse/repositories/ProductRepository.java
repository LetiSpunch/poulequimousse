package fr.laPouleQuiMousse.repositories;

import fr.laPouleQuiMousse.models.Enums.EProductState;
import fr.laPouleQuiMousse.models.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("orderRepository")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, QueryByExampleExecutor<Product>, JpaSpecificationExecutor<Product> {

        List<Product> findByState(EProductState state);
    }
