package web.model.service;

import java.util.List;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Category;

public interface CategoryService {
	
	/**
	 * create category
	 * */
	public Category create(Category category, Account owner);
	
	
	
	/**
	 * remove category and its articles
	 * return the count of removed articles
	 * */
	public int remove(String categoryId, Account owner);
	
	public Category update(Category category, Account owner);
	
	public List<Category> getCategoriesOwnedBy(Account account);
	
	
	
	
	public List<Category> updateAll(List<Category> categories);
	public Category get(String id);
	

}
