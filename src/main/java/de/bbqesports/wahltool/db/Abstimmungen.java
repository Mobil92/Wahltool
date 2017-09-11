package de.bbqesports.wahltool.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Abstimmungen")
public class Abstimmungen extends EntityModel {

	@Column(length = 65535)
	private String abstimmungsText;

	private boolean aktuell;

	@OneToMany(mappedBy = "abstimmungen")
	private Set<AbstimmungenUser> abstimmungenUser = new HashSet<AbstimmungenUser>();

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
