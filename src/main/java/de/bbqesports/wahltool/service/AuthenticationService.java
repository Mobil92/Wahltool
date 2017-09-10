package de.bbqesports.wahltool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.UserRechte;

@Service
public class AuthenticationService {

	@Autowired
	private UserRechteService userRechteService;

	private UserRechte userRecht;

	public boolean login(String bBenutzer, String pw) {
		Optional<UserRechte> userRechte = userRechteService.findByBBenutzer(bBenutzer);

		if (userRechte.isPresent()) {
			userRecht = userRechte.get();
		} else {
			return false;
		}

		return true;
	}

	public UserRechte getUserRecht() {
		return userRecht;
	}

	public void setUserRecht(UserRechte userRecht) {
		this.userRecht = userRecht;
	}

	public boolean isUserLoggedIn() {
		return userRecht != null;
	}

	public void logout() {
		userRecht = null;
	}

}
