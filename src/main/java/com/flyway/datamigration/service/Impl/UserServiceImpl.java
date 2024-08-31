package com.flyway.datamigration.service.Impl;

import com.flyway.datamigration.entity.User;
import com.flyway.datamigration.repository.UserRepository;
import com.flyway.datamigration.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User object) {
		return userRepository.save(object);

	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long aLong) {
		return userRepository.findById(aLong).orElse(null);
	}

	@Override
	public void deleteById(Long aLong) {
		userRepository.deleteById(aLong);
	}

	@Override
	public void update(User object) {
		Optional<User> byId = userRepository.findById(object.getId());
		if (byId.isPresent()){
			userRepository.save(object);
		}

	}
}
