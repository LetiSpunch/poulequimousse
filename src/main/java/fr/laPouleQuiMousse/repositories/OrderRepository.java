package fr.laPouleQuiMousse.repositories;
import fr.laPouleQuiMousse.models.Enums.EOrderState;
import fr.laPouleQuiMousse.models.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository("orderRepository")
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>, QueryByExampleExecutor<Order>, JpaSpecificationExecutor<Order> {

    List<Order> findByState(EOrderState state);
}
