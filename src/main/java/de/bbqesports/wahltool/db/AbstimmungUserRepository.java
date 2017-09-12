package de.bbqesports.wahltool.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstimmungUserRepository extends JpaRepository<AbstimmungUser, Long> {

}
