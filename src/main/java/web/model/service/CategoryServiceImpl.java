package web.model.service;

import java.util.ArrayList;
import java.util.List;

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
		//todo validate
		return categoryRepo.saveAndFlush(category);
	}
	
	@Override
	public Category update(Category category) {
		//todo validate
		return categoryRepo.saveAndFlush(category);
	}

	@Override
	public Category get(String id) {
		return categoryRepo.getOne(id);
	}

	@Override
	public void remove(String categoryId, Long ownerId) {
		Category tagetCategory = categoryRepo.getOne(categoryId);
		List<Category> allOwned = this.getCategoriesOwnedBy(tagetCategory.getAccount());
		System.out.println(getAllChildrenCategory(tagetCategory, allOwned));
	}
	
	private List<Category> getAllChildrenCategory(Category target, List<Category> catList) {
		boolean hasMoreChildren = true;
		List<String> curLevelChildrenIds = new ArrayList<String>();
		List<String> nextLevelChildrenIds = new ArrayList<String>();
		List<Category> ret = new ArrayList<Category>();
		curLevelChildrenIds.add(target.getId());
		while(hasMoreChildren) {
			for(Category cat : catList) {
				for(String catId : curLevelChildrenIds) {
					if(cat.getParentId().equals(catId)) {
						ret.add(cat);
						nextLevelChildrenIds.add(catId);
					}
				}
			}
			
			hasMoreChildren = (nextLevelChildrenIds.isEmpty())? false : true;
			curLevelChildrenIds.clear();
			curLevelChildrenIds.addAll(nextLevelChildrenIds);
			nextLevelChildrenIds.clear();
		}
		return ret;
	}
	

	

	@Override
	public List<Category> getCategoriesOwnedBy(Account account) {
		Query q = em.createQuery("SELECT category FROM Category category "
								+"WHERE category.account.id = :accountId");
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
