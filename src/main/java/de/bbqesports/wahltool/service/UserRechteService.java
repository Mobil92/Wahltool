package de.bbqesports.wahltool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.UserRechte;
import de.bbqesports.wahltool.db.UserRechteRepository;

@Service
public class UserRechteService extends AbstractService<UserRechte, UserRechteRepository> {

	public Optional<UserRechte> findByBBenutzer(String bBenutzer) {
		return Optional.ofNullable(repository.findByBBenutzerIgnoreCase(bBenutzer));
	}

	public List<UserRechte> findAll() {
		return repository.findAll();
	}

}
