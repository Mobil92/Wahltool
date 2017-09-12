package de.bbqesports.wahltool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.User;

@Service
public class AuthenticationService {

	@Autowired
	private UserService userService;

	private User user;

	public boolean login(String bBenutzer) {
		Optional<User> optionalUser = userService.findByUserName(bBenutzer);

		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		} else {
			return false;
		}

		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUserLoggedIn() {
		return user != null;
	}

	public void logout() {
		user = null;
	}

}
