package de.bbqesports.wahltool.db;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "UserRechte", uniqueConstraints = @UniqueConstraint(columnNames = { "bBenutzer" }))
public class UserRechte extends EntityModel {

	private String bBenutzer;

	private boolean rechtAdmin;

	@ManyToMany
	@JoinTable(name = "USER_ABSTIMMUNGEN", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ABSTIMMUNG_ID", referencedColumnName = "id"))
	private Set<Abstimmungen> abstimmungen;

	public UserRechte(String bBenutzer, boolean rechtAdmin) {
		super();
		this.bBenutzer = bBenutzer;
		this.rechtAdmin = rechtAdmin;
	}

	public UserRechte(String bBenutzer) {
		super();
		this.bBenutzer = bBenutzer;

	}

	public UserRechte() {
	}

	public String getbBenutzer() {
		return bBenutzer;
	}

	public void setbBenutzer(String bBenutzer) {
		this.bBenutzer = bBenutzer;
	}

	public boolean isRechtAdmin() {
		return rechtAdmin;
	}

	public void setRechtAdmin(boolean rechtAdmin) {
		this.rechtAdmin = rechtAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bBenutzer == null) ? 0 : bBenutzer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRechte other = (UserRechte) obj;
		if (bBenutzer == null) {
			if (other.bBenutzer != null)
				return false;
		} else if (!bBenutzer.equals(other.bBenutzer))
			return false;
		return true;
	}

}
