package de.bbqesports.wahltool.db;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AbstimmungenUser")
public class AbstimmungenUser extends EntityModel {

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Abstimmungen getAbstimmungen() {
		return abstimmungen;
	}

	public void setAbstimmungen(Abstimmungen abstimmungen) {
		this.abstimmungen = abstimmungen;
	}

	@ManyToOne
	@JoinColumn(name = "abstimmungen")
	private Abstimmungen abstimmungen;

	@Enumerated(EnumType.STRING)
	private Stimme stimme;

	public Stimme getStimme() {
		return stimme;
	}

	public void setStimme(Stimme stimme) {
		this.stimme = stimme;
	}

}
