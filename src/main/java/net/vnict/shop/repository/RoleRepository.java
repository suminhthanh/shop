package net.vnict.shop.repository;

import net.vnict.shop.domain.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	Role findByName(String name);

}
