package de.bbqesports.wahltool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.Abstimmungen;
import de.bbqesports.wahltool.db.AbstimmungenRepository;

@Service
public class AbstimmungenService extends AbstractService<Abstimmungen, AbstimmungenRepository> {

	private String stringAbstimmung;

	public Optional<Abstimmungen> findById(long id) {
		return Optional.ofNullable(repository.findById(id));
	}

	public List<Abstimmungen> findAll() {
		return repository.findAll();
	}

	public void setAllInactiv() {
		repository.findAll().stream().filter(w -> w.isAktuell()).forEach(abstimmung -> {
			abstimmung.setAktuell(false);
			save(abstimmung);
		});
	}

	public String findAktuelleAbstimmung() {
		repository.findAll().stream().filter(w -> w.isAktuell()).forEach(abstimmung -> {
			stringAbstimmung = abstimmung.getAbstimmungsText();
		});
		return stringAbstimmung;
	}
}
