package com.flyway.datamigration.service.Impl;

import com.flyway.datamigration.entity.Role;
import com.flyway.datamigration.repository.RoleRepository;
import com.flyway.datamigration.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Role save(Role object) {
		Optional<Role> role = roleRepository.findByRoleName(object.getRoleName());
		if(role.isPresent())   throw new IllegalArgumentException("Role with name " + object.getRoleName() + " already exists.");
		return roleRepository.save(object);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long aLong) {
		return roleRepository.findById(aLong).orElse(null);
	}

	@Override
	public void deleteById(Long aLong) {
		roleRepository.deleteById(aLong);

	}

	@Override
	public void update(Role object) {
		Optional<Role> byId = roleRepository.findById(object.getId());
		if (byId.isPresent()) this.save(object);

	}
}
