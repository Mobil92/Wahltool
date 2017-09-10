package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import db.UserRechte;
import db.UserRechteRepository;

@Service
public class UserRechteService extends AbstractService<UserRechte, UserRechteRepository> {

	public Optional<UserRechte> findByBBenutzer(String bBenutzer) {
		return Optional.ofNullable(repository.findByBBenutzerIgnoreCase(bBenutzer));
	}

	public List<UserRechte> findAll() {
		return repository.findAll();
	}

}
