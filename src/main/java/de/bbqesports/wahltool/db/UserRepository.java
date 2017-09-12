package de.bbqesports.wahltool.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaadin.spring.annotation.UIScope;

@Repository
@UIScope
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserNameIgnoreCase(String userName);

}
