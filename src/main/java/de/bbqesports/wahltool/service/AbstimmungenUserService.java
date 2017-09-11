package de.bbqesports.wahltool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.AbstimmungenUser;
import de.bbqesports.wahltool.db.AbstimmungenUserRepository;

@Service
public class AbstimmungenUserService extends AbstractService<AbstimmungenUser, AbstimmungenUserRepository> {

	public List<AbstimmungenUser> findAll() {
		return repository.findAll();
	}

}
