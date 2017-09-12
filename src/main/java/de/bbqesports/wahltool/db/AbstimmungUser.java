package de.bbqesports.wahltool.db;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "AbstimmungUser", uniqueConstraints = @UniqueConstraint(columnNames = { "user", "abstimmung" }))
public class AbstimmungUser extends EntityModel {

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "abstimmung")
	private Abstimmung abstimmung;

	public User getUser() {
		return user;
	}

	public String getUserString() {
		return user.getUserName();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Abstimmung getAbstimmungen() {
		return abstimmung;
	}

	public long getAbstimmungLong() {
		return abstimmung.getId();
	}

	public String getAbstimmungString() {
		return abstimmung.getAbstimmungsText();
	}

	public void setAbstimmungen(Abstimmung abstimmung) {
		this.abstimmung = abstimmung;
	}

	@Enumerated(EnumType.STRING)
	private Stimme stimme;

	public Stimme getStimme() {
		return stimme;
	}

	public void setStimme(Stimme stimme) {
		this.stimme = stimme;
	}

}
