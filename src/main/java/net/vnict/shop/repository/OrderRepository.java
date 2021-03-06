package net.vnict.shop.repository;

import net.vnict.shop.domain.entities.Order;
import net.vnict.shop.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	@Query("select distinct o from Order o left join fetch o.user left join fetch o.items i left join fetch i.pk.product")
	List<Order> findAll();
	
	@Query("select distinct o from Order o left join fetch o.user left join fetch o.items i left join fetch i.pk.product where o.user = ?1")
	List<Order> findByUser(User user);
	
	@Query(value = "select o from Order o left join fetch o.user left join fetch o.items i left join fetch i.pk.product",
			countQuery = "select count(o) from Order o")
    Page<Order> findLatest(Pageable pageable);
	
	@Query("select o from Order o left join fetch o.user left join fetch o.items i left join fetch i.pk.product where o.id = ?1")
	Order findOne(Integer id);

}
