package de.bbqesports.wahltool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.bbqesports.wahltool.db.Abstimmung;
import de.bbqesports.wahltool.db.AbstimmungRepository;
import de.bbqesports.wahltool.db.Auswertung;
import de.bbqesports.wahltool.db.Stimme;

@Service
public class AbstimmungService extends AbstractService<Abstimmung, AbstimmungRepository> {

	private String stringAbstimmung;

	private Abstimmung abstimmung;

	int ja = 0;
	int nein = 0;
	int enthaltung = 0;

	public List<Auswertung> findAllAuswertungen() {
		List<Auswertung> auswertungenList = new ArrayList<Auswertung>();

		repository.findAll().stream().forEach(abstimmung -> {
			ja = 0;
			nein = 0;
			enthaltung = 0;

			abstimmung.getAbstimmungUser().stream().forEach(stimme -> {
				if (stimme.getStimme() == Stimme.JA) {
					ja++;
				} else if (stimme.getStimme() == Stimme.NEIN) {
					nein++;
				} else if (stimme.getStimme() == Stimme.ENTHALTUNG) {
					enthaltung++;
				}
			});
			auswertungenList.add(new Auswertung(abstimmung, ja, nein, enthaltung));
		});

		return auswertungenList;
	}

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
