package de.bbqesports.wahltool.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstimmungenUserRepository extends JpaRepository<AbstimmungenUser, Long> {

}
