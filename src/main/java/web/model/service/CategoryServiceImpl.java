package web.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;
import web.model.jpa.repos.ArticleRepo;
import web.model.jpa.repos.CategoryRepo;
import web.utils.UUIDUtil;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ArticleRepo articleRepo;
	
	@Autowired
	EntityManager em;
	
	@Transactional
	@Override
	public Category create(Category category, Account owner) {
		validateCategory(category, owner);
		
		category.setId(UUIDUtil.getUUID());
		category.setOwner(owner);
		return categoryRepo.save(category);
	}
	
	private void validateCategory(Category cat, Account owner) {
		if(cat == null) { throw new IllegalStateException("Null Category"); }
		if(cat.getName().length() > 50) { throw new IllegalStateException("Too long Category name"); }

		List<Category> sibilings = categoryRepo.findChildrenByIdAndOwner(cat.getParentId(), owner.getId());
		for(Category sib : sibilings) {
			if(sib.getName().equals(cat.getName())) {				
				if(sib.getId().equals(cat.getId())) {
					
				}else {
					throw new IllegalStateException("이미 하위에 같은 이름의 카테고리가 있습니다.");
				}
			}
		}
		
	}
	
	@Transactional
	@Override
	public Category update(Category category, Account owner) {
		validateCategory(category, owner);
		
		Category old = categoryRepo.findByIdAndOwner(category.getId(), owner.getId());
		old.setName(category.getName());
		old.setIsPublic(category.getIsPublic());
		return categoryRepo.save(old);
	}

	@Override
	public Category get(String id) {
		return categoryRepo.getOne(id);
	}
	
	@Transactional
	@Override
	public int remove(String categoryId, Account owner) {
		Category tagetCategory = categoryRepo.findByIdAndOwner(categoryId, owner.getId());
		List<Category> allOwned = categoryRepo.findCategoriesOwnedBy(owner.getId());
		List<Category> targetChildren = getDescendantsFromCategoryList(tagetCategory, allOwned);
		
		tagetCategory.setIsDel(true);
		targetChildren.forEach(el -> el.setIsDel(true));
		
		//merge target and its descendants  
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
	
	private List<Category> getDescendantsFromCategoryList(Category target, List<Category> catList) {
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
	public List<Category> getCategoriesByOwenerUsername(String username) {
		List<Category> cats = categoryRepo.findCategoriesByOwnerUsername(username);
		return cats;
	}


	@Override
	public List<Category> updateAll(List<Category> categories) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
