package web.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;
import web.model.jpa.repos.ArticleRepo;
import web.model.jpa.repos.CategoryRepo;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ArticleRepo articleRepo;
	
	@Autowired
	EntityManager em;
	
	@Override
	public Category create(Category category) {
		//TODO validate
		return categoryRepo.saveAndFlush(category);
	}
	
	@Override
	public Category update(Category category) {
		//TODO validate
		return categoryRepo.saveAndFlush(category);
	}

	@Override
	public Category get(String id) {
		return categoryRepo.getOne(id);
	}
	
	@Transactional
	@Override
	public int remove(String categoryId, Long ownerId) {
		Category tagetCategory = categoryRepo.getOne(categoryId);
		List<Category> allOwned = this.getCategoriesOwnedBy(tagetCategory.getOwner());
		List<Category> targetChildren = getAllChildrenCategoryFromCategoryList(tagetCategory, allOwned);
		
		tagetCategory.setIsDel(true);
		targetChildren.forEach(el -> el.setIsDel(true));
		
		targetChildren.add(tagetCategory);
		List<Category> targets = targetChildren;
		//remove categories
		categoryRepo.save(targets);
		
		//remove articles
		List<Article> targetArticles = articleRepo.getArticlesOf(targets);
		targetArticles.forEach(el -> el.setIsDel(true));
		articleRepo.save(targetArticles);
		
		return targetArticles.size();
	}
	
	private List<Category> getAllChildrenCategoryFromCategoryList(Category target, List<Category> catList) {
		List<Category> ret = new ArrayList<Category>();
		
		boolean hasMoreChildren = true;
		List<String> nextLevelChildrenIds = new ArrayList<String>();
		List<String> curLevelChildrenIds = new ArrayList<String>();
		curLevelChildrenIds.add(target.getId());
		
		while(hasMoreChildren) {
			for(Category cat : catList) {
				for(String catId : curLevelChildrenIds) {
					if(catId.equals(cat.getParentId())) {
						ret.add(cat);
						nextLevelChildrenIds.add(cat.getId());
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
		List<Category> cats = categoryRepo.findCategoriesOwnedBy(account.getId());
		return cats;
	}

	@Override
	public List<Category> updateAll(List<Category> categories) {
		// TODO Auto-generated method stub
		return null;
	}

}
