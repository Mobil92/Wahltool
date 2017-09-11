package de.bbqesports.wahltool.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByBBenutzerIgnoreCase(String bBenutzer);

}