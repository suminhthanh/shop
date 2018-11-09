package net.vnict.shop.repository;

import net.vnict.shop.domain.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("select u from User u")
	List<User> findAll();
	
	@Query("from User u left join fetch u.roles where u.email = ?1")
    User findByEmail(String email);
}
