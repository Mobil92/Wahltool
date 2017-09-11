package de.bbqesports.wahltool.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = { "bBenutzer" }))
public class User extends EntityModel {

	private String bBenutzer;

	private boolean rechtAdmin;

	@OneToMany(mappedBy = "user")
	private Set<AbstimmungenUser> abstimmungenUser = new HashSet<AbstimmungenUser>();

	public User(String bBenutzer, boolean rechtAdmin) {
		super();
		this.bBenutzer = bBenutzer;
		this.rechtAdmin = rechtAdmin;
	}

	public User(String bBenutzer) {
		super();
		this.bBenutzer = bBenutzer;

	}

	public User() {
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
		User other = (User) obj;
		if (bBenutzer == null) {
			if (other.bBenutzer != null)
				return false;
		} else if (!bBenutzer.equals(other.bBenutzer))
			return false;
		return true;
	}

}
