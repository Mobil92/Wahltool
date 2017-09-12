package de.bbqesports.wahltool.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import de.bbqesports.wahltool.db.EntityModel;

public class AbstractService<E extends EntityModel, R extends JpaRepository<E, Long>> {

	@Autowired
	protected R repository;

	@Autowired
	protected EntityManager entityManager;

	public Optional<E> findOne(long id) {
		return Optional.ofNullable(repository.findOne(id));
	}

	public List<E> findAll() {
		return repository.findAll();
	}

	public Optional<E> save(E e) {

		try {
			return Optional.ofNullable(repository.save(e));

		} catch (Exception ex) {
			String message = null;

			if (ex instanceof DataIntegrityViolationException) {
				String exception = ((DataIntegrityViolationException) ex).getRootCause().getMessage();

				if (exception.contains("Unique index or primary key violation")) {
					message = "Ein solches Element existiert bereits und kann daher nicht gespeichert werden.";
				}
			}

			showError(Optional.ofNullable(message).orElse("Das Element konnte nicht gespeichert werden."));

			return Optional.empty();
		}
	}

	public boolean delete(E e) {
		try {
			repository.delete(e);
			return true;
		} catch (Exception ex) {
			String message = null;

			if (ex instanceof DataIntegrityViolationException) {
				String exception = ((DataIntegrityViolationException) ex).getRootCause().getMessage();

				if (exception.contains("Referential integrity constraint violation")) {
					message = "Das Element darf nicht gelöscht werden, da bereits Datensätze mit ihm Verknüpft sind.\n"
							+ "Bitte löschen Sie zuerst diese Datensätze oder wenden Sie sich an den Administrator.";
				}
			}

			showError(Optional.ofNullable(message).orElse("Das Element konnte nicht gelöscht werden."));

			return false;
		}
	}

	private void showError(String message) {
		if (UI.getCurrent() != null) {
			Notification.show(message, Notification.Type.WARNING_MESSAGE);
		} else {
			System.out.println(message);
		}
	}
}
