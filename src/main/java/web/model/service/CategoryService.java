package web.model.service;

import java.util.List;
import java.util.Set;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Category;

public interface CategoryService {
	
	public Category create(Category category);
	
	public Category get(String id);
	
	/**
	 * remove category and its articles
	 * return the count of removed articles
	 * */
	public int remove(String categoryId, Long ownerId);
	
	public Category update(Category category);
	
	public List<Category> getCategoriesOwnedBy(Account account);
	
	public List<Category> updateAll(List<Category> categories);
	

}
