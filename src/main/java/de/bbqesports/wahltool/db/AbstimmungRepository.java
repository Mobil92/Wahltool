package de.bbqesports.wahltool.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstimmungRepository extends JpaRepository<Abstimmung, Long> {

	Abstimmung findById(long id);

}
