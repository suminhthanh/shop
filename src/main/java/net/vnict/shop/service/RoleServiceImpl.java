package net.vnict.shop.service;

import net.vnict.shop.domain.entities.Role;
import net.vnict.shop.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	
}
