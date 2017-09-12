package de.bbqesports.wahltool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.User;
import de.bbqesports.wahltool.db.UserRepository;

@Service
public class UserService extends AbstractService<User, UserRepository> {

	public Optional<User> findByUserName(String userName) {
		return Optional.ofNullable(repository.findByUserNameIgnoreCase(userName));
	}

	public List<User> findAll() {
		return repository.findAll();
	}

}
