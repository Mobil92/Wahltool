package de.bbqesports.wahltool.db;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Abstimmungen")
public class Abstimmungen extends EntityModel {

	@Column(length = 65535)
	private String abstimmungsText;

	private boolean aktuell;

	// @Enumerated(EnumType.STRING)
	// private Stimme stimme;
	//
	// public Stimme getStimme() {
	// return stimme;
	// }
	//
	// public void setStimme(Stimme stimme) {
	// this.stimme = stimme;
	// }

	@ManyToMany(mappedBy = "abstimmungen")
	private Set<UserRechte> userRechte;

	public Abstimmungen(String abstimmungsText, boolean aktuell) {
		super();
		this.abstimmungsText = abstimmungsText;
		this.aktuell = aktuell;
	}

	public Abstimmungen() {
	}

	public String getAbstimmungsText() {
		return abstimmungsText;
	}

	public void setAbstimmungsText(String abstimmungsText) {
		this.abstimmungsText = abstimmungsText;
	}

	public boolean isAktuell() {
		return aktuell;
	}

	public void setAktuell(boolean aktuell) {
		this.aktuell = aktuell;
	}

}
