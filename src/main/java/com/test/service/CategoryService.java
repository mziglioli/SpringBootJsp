package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.model.Category;
import com.test.repository.CategoryRepository;

@Service
public class CategoryService extends ServiceDefault<Category, CategoryRepository> {

	@Autowired
	private ProductService productService;

	public List<Category> findCategoriesForProduct() {
		return repository.findAll();
	}

	@Override
	protected boolean beforeDelete(ModelAndView model, Category entity, RedirectAttributes redir) {
		if (productService.hasProductWithCategory(entity)) {
			List<ObjectError> errors = new ArrayList<>();
			errors.add(new ObjectError("category", "error.dependency.category"));
			model.addObject("errors", errors);
			redir.addAttribute("errors", errors);
			return false;
		}
		return true;
	}

}