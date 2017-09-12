package de.bbqesports.wahltool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.AbstimmungUser;
import de.bbqesports.wahltool.db.AbstimmungUserRepository;
import de.bbqesports.wahltool.db.Stimme;

@Service
public class AbstimmungUserService extends AbstractService<AbstimmungUser, AbstimmungUserRepository> {

	private int counter;
	
	public List<AbstimmungUser> findAll() {
		return repository.findAll();
	}

	public int findVotesWithYes() {
		counter = 0;
		repository.findAll().stream().filter(w -> w.getStimme().equals(Stimme.JA)).forEach(abstimmung -> {
			counter++;
		});
		return counter;
	}

}
