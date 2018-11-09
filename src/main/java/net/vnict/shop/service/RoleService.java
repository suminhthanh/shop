package net.vnict.shop.service;

import net.vnict.shop.domain.entities.Role;

public interface RoleService {

	Role findByName(String name);
	
}
