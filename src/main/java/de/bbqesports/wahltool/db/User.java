package de.bbqesports.wahltool.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = { "userName" }))
public class User extends EntityModel {

	private String userName;

	private boolean rechtAdmin;

	@OneToMany(mappedBy = "user")
	private Set<AbstimmungUser> abstimmungUser = new HashSet<AbstimmungUser>();

	public User(String userName, boolean rechtAdmin) {
		super();
		this.userName = userName;
		this.rechtAdmin = rechtAdmin;
	}

	public User(String userName) {
		super();
		this.userName = userName;

	}

	public User() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
