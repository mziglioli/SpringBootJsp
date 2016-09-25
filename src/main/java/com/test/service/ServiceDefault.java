package com.test.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.model.EntityJpaClass;

import lombok.Getter;

public abstract class ServiceDefault<T extends EntityJpaClass, R extends JpaRepository<T, Long>> {

	@Autowired
	@Getter
	protected R repository;

	public void save(ModelAndView model, T entity, RedirectAttributes redir) {
		try {
			if (entity.getId() == null) {
				beforeInsert(model, entity, redir);
				repository.save(entity);
				redir.addFlashAttribute("success", "label.successfully.created");
				model.addObject("success", "label.successfully.created");
				afterInsert(model, entity, redir);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("error", e.getMessage());
		}
	}

	public void save(T entity) {
		try {
			if (entity.getId() == null) {
				beforeInsert(entity);
				repository.save(entity);
				afterInsert(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(ModelAndView model, T entity, RedirectAttributes redir) {
		try {
			beforeUpdate(model, entity, redir);
			repository.save(entity);
			model.addObject("success", "label.successfully.updated");
			redir.addFlashAttribute("success", "label.successfully.updated");
			afterUpdate(model, entity, redir);
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("error", e.getMessage());
		}
	}

	public void update(T entity) {
		try {
			beforeUpdate(entity);
			repository.save(entity);
			afterUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(T entity) {
		try {
			beforeDelete(entity);
			repository.delete(entity);
			afterDelete(entity);
		} catch (Exception e) {
			e.printStackTrace();
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

	public void delete(Long id) {
		T entity = repository.findOne(id);
		delete(entity);
	}

	public boolean delete(ModelAndView model, Long id, RedirectAttributes redir) {
		T entity = repository.findOne(id);
		return delete(model, entity, redir);
	}

	public Page<T> findByPageable(Pageable page) {
		// return repository.findAll(page);
		return repository.findAll(new PageRequest(page.getPageNumber(), 2));
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

	}

	protected void afterUpdate(T entity) {
	}

	protected void afterUpdate(ModelAndView model, T entity, RedirectAttributes redir) {
	}

	protected void afterDelete(T entity) {
	}

	protected void afterDelete(ModelAndView model, T entity, RedirectAttributes redir) {
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