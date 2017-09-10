package db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRechteRepository extends JpaRepository<UserRechte, Long> {

	UserRechte findByBBenutzerIgnoreCase(String bBenutzer);

}
