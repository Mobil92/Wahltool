package de.bbqesports.wahltool.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

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

		} catch (DataIntegrityViolationException ex) {
			return Optional.empty();
		}
	}

	public void delete(E e) {
		repository.delete(e);
	}
}
