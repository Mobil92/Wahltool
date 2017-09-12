package de.bbqesports.wahltool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.AbstimmungUser;
import de.bbqesports.wahltool.db.AbstimmungUserRepository;

@Service
public class AbstimmungUserService extends AbstractService<AbstimmungUser, AbstimmungUserRepository> {

	public List<AbstimmungUser> findAll() {
		return repository.findAll();
	}

}
