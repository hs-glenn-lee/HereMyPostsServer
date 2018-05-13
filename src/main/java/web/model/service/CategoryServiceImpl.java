package web.model.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Category;
import web.model.jpa.repos.CategoryRepo;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	EntityManager em;
	
	@Override
	public Category create(Category category) {
		return categoryRepo.saveAndFlush(category);
	}

	@Override
	public Category get(String id) {
		return categoryRepo.getOne(id);
	}

	@Override
	public void  delete(Category category) {
		categoryRepo.delete(category);
	}

	@Override
	public Category update(Category category) {
		Category updated = em.merge(category);
		return updated;
	}

	@Override
	public List<Category> getCategoriesOwnedBy(Account account) {
		Query q = em.createQuery("SELECT category FROM Category category "
								+"WHERE category.account.id = :accountId");
		System.out.println(account.getId());
		q.setParameter("accountId", account.getId());
		List<Category> cats = (List<Category>)q.getResultList();
		return cats;
	}

	@Override
	public List<Category> updateAll(List<Category> categories) {
		// TODO Auto-generated method stub
		return null;
	}

}
