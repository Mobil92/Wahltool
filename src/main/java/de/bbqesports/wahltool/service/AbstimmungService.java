package de.bbqesports.wahltool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.Abstimmung;
import de.bbqesports.wahltool.db.AbstimmungRepository;

@Service
public class AbstimmungService extends AbstractService<Abstimmung, AbstimmungRepository> {

	private String stringAbstimmung;

	private Abstimmung abstimmung;

	public Optional<Abstimmung> findById(long id) {
		return Optional.ofNullable(repository.findById(id));
	}

	public List<Abstimmung> findAll() {
		return repository.findAll();
	}

	public void setAllInactiv() {
		repository.findAll().stream().filter(w -> w.isAktuell()).forEach(abstimmung -> {
			abstimmung.setAktuell(false);
			save(abstimmung);
		});
	}

	public String findAktuelleAbstimmungString() {
		repository.findAll().stream().filter(w -> w.isAktuell()).forEach(abstimmung -> {
			stringAbstimmung = abstimmung.getAbstimmungsText();
		});
		return stringAbstimmung;
	}

	public Abstimmung findAktuelleAbstimmung() {
		repository.findAll().stream().filter(w -> w.isAktuell()).forEach(abstimmung -> {
			this.abstimmung = abstimmung;
		});
		return this.abstimmung;
	}
}
