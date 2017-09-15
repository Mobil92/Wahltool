package de.bbqesports.wahltool.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Abstimmung")
public class Abstimmung extends EntityModel {

	@Column(length = 65535)
	private String abstimmungsText;

	private String abstimmungTitel;

	private boolean aktuell = false;

	@OneToMany(mappedBy = "abstimmung", fetch = FetchType.EAGER)
	private Set<AbstimmungUser> abstimmungUser = new HashSet<AbstimmungUser>();

	public Set<AbstimmungUser> getAbstimmungUser() {
		return abstimmungUser;
	}

	public void setAbstimmungUser(Set<AbstimmungUser> abstimmungUser) {
		this.abstimmungUser = abstimmungUser;
	}

	public Abstimmung(String abstimmungTitel, String abstimmungsText, boolean aktuell) {
		super();
		this.abstimmungTitel = abstimmungTitel;
		this.abstimmungsText = abstimmungsText;
		this.aktuell = aktuell;
	}

	public Abstimmung() {
		this(null, null, false);
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

	public String getAbstimmungTitel() {
		return abstimmungTitel;
	}

	public void setAbstimmungTitel(String abstimmungTitel) {
		this.abstimmungTitel = abstimmungTitel;
	}

}
