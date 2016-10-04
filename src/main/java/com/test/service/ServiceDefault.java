package com.test.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.model.EntityJpaClass;

import lombok.Getter;

public abstract class ServiceDefault<T extends EntityJpaClass, R extends JpaRepository<T, Long>> {

	@Autowired
	@Getter
	protected R repository;

	public boolean save(ModelAndView model, T entity, RedirectAttributes redir) {
		if (isUnique(entity)) {
			if (entity.getId() == null) {
				beforeInsert(model, entity, redir);
				repository.save(entity);
				afterInsert(model, entity, redir);
			} else {
				update(model, entity, redir);
			}
			return true;
		} else {
			addUniqueError(model, entity);
		}
		return false;
	}

	protected void addUniqueError(ModelAndView model, T entity) {

	}

	public void save(T entity) {
		try {
			if (entity.getId() == null) {
				beforeInsert(entity);
				repository.save(entity);
				afterInsert(entity);
			} else {
				update(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(ModelAndView model, T entity, RedirectAttributes redir) {
		beforeUpdate(model, entity, redir);
		repository.save(entity);
		model.addObject("success", "label.successfully.updated");
		redir.addFlashAttribute("success", "label.successfully.updated");
		afterUpdate(model, entity, redir);
	}

	private void update(T entity) {
		try {
			beforeUpdate(entity);
			repository.save(entity);
			afterUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean delete(T entity) {
		try {
			beforeDelete(entity);
			repository.delete(entity);
			afterDelete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(ModelAndView model, T entity, RedirectAttributes redir) {
		try {
			if (beforeDelete(model, entity, redir)) {
				repository.delete(entity);
				redir.addFlashAttribute("success", "label.successfully.deleted");
				model.addObject("success", "label.successfully.deleted");
				afterDelete(model, entity, redir);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("error", e.getMessage());
		}
		return false;
	}

	public boolean delete(Long id) {
		T entity = repository.findOne(id);
		return delete(entity);
	}

	public boolean delete(ModelAndView model, Long id, RedirectAttributes redir) {
		T entity = repository.findOne(id);
		return delete(model, entity, redir);
	}

	public Collection<T> findAll() {
		return repository.findAll();
	}

	public T findById(Long id) {
		return repository.findOne(id);
	}

	protected void afterInsert(T entity) {

	}

	protected void afterInsert(ModelAndView model, T entity, RedirectAttributes redir) {
		redir.addFlashAttribute("success", "label.successfully.created");
	}

	protected void afterUpdate(T entity) {
	}

	protected void afterUpdate(ModelAndView model, T entity, RedirectAttributes redir) {
		redir.addFlashAttribute("success", "label.successfully.updated");
	}

	protected void afterDelete(T entity) {
	}

	protected void afterDelete(ModelAndView model, T entity, RedirectAttributes redir) {
		redir.addFlashAttribute("success", "label.successfully.deleted");
	}

	protected void beforeInsert(T entity) {
	}

	protected void beforeInsert(ModelAndView model, T entity, RedirectAttributes redir) {
	}

	protected void beforeUpdate(T entity) {
	}

	protected void beforeUpdate(ModelAndView model, T entity, RedirectAttributes redir) {
	}

	protected boolean beforeDelete(T entity) {
		return true;
	}

	protected boolean beforeDelete(ModelAndView model, T entity, RedirectAttributes redir) {
		return true;
	}

	protected boolean isUnique(T entity) {
		return true;
	}
}