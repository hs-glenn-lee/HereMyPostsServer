package web.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.AccountSetting;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Tag;
import web.model.jpa.entities.TagArticle;
import web.model.jpa.repos.AccountSettingRepo;
import web.model.jpa.repos.ArticleRepo;
import web.model.jpa.repos.TagArticleRepo;
import web.model.jpa.repos.TagRepo;
import web.model.service.article.ArticleService;
import web.model.service.sign.SignService;
import web.utils.UUIDUtil;

@Service("tagService")
public class TagServiceImpl  implements TagService{
	
	@Autowired
	EntityManager em;
	
	@Autowired
	TagRepo tagRepo;
	
	@Autowired
	TagArticleRepo tagArticleRepo;
	
	@Autowired
	SignService signService;
	
	@Autowired
	AccountSettingService accountSettingService;
	
	@Autowired
	AccountSettingRepo accountSettingRepo;

	@Autowired
	ArticleRepo articleRepo;
	
	@Autowired 
	ArticleService articleService;
	
	//--------tag
	@Override
	public List<Tag> findTagsByArticle(String articleId) {
		List<Tag> tags = tagRepo.findByArticleId(articleId);
		return tags;
	}

	@Override
	public List<Tag> saveTags(List<Tag> tags) {//TODO performance
		return tagRepo.save(tags);
	}
	
	
	//-----------tagarticle
	@Override
	public List<TagArticle> findTagArticlesByArticleId(String articleId) {
		return tagArticleRepo.findByArticleId(articleId);
	}
	
	@Transactional
	@Override
	public List<TagArticle> updateTagsArticlesOfArticle(String targetArticleId, List<TagArticle> newTagsArticles) {
		Article targetArticle = articleRepo.findOne(targetArticleId);
		
		validateInputSaveTagsArticlesOfArticle(targetArticle, newTagsArticles);
		
		//set old tag articles
		List<TagArticle> oldTagArticles = tagArticleRepo.findByArticleId(targetArticleId);
		HashSet<String> oldTsAsIds = new HashSet<String>();
		for(TagArticle oldTa : oldTagArticles) {
			oldTsAsIds.add(oldTa.getId());
		}
		
		//set newTagsArticles
		HashSet<String> newTsAsIds = new HashSet<String>();
		for(TagArticle ta : newTagsArticles) {
			if(ta.getId() == null) {
				ta.setId(UUIDUtil.getUUID());
			}
			ta.setArticle(targetArticle);
			newTsAsIds.add(ta.getId());
		}
		
		//get new ta to save
		List<Tag> tags = new ArrayList<Tag>();
		List<TagArticle> newToSave = new ArrayList<TagArticle>();
		for(TagArticle ta : newTagsArticles) {
			if(!oldTsAsIds.contains(ta.getId())) {
				tags.add(ta.getTag());
				newToSave.add(ta);
			}
		}
		
		//get old ta to delete
		List<TagArticle> oldToDelete = new ArrayList<TagArticle>();
		for(TagArticle oldTa : oldTagArticles) {
			if(!newTsAsIds.contains(oldTa.getId())) {
				oldToDelete.add(oldTa);
			}
		}

		tagRepo.save(tags);
		tagArticleRepo.save(newToSave);
		tagArticleRepo.delete(oldToDelete);
		
		//save my tags
		AccountSetting authorSetting = targetArticle.getAuthor().getAccountSetting();
		this.addMyTags(authorSetting, tags);

		return newTagsArticles;
	}
	
	public void validateInputSaveTagsArticlesOfArticle(Article targetArticle, List<TagArticle> tagsArticles) {
		if(tagsArticles == null) {
			throw new IllegalStateException("tagsArticles must not be null.");
		}
		if(targetArticle == null) {
			throw new IllegalStateException("targetArticle not found.");
		}

		for(TagArticle ta : tagsArticles) {
			Article test = ta.getArticle();
			if(test == null) {
				throw new IllegalStateException("article in tagsArticles id null");
			}
			if(!targetArticle.getId().equals(test.getId())) {
				throw new IllegalStateException("article in tagsArticles id must equal to targetArticleId");
			}
		}
		
	}

	//-----------mytags
	@Transactional
	@Override
	public AccountSetting addMyTags(AccountSetting setting, List<Tag> tags) {
		MyTagUtils myTagUtil = new MyTagUtils(setting.getMyTags());
		myTagUtil.addTags(tags);
		setting.setMyTags(myTagUtil.getTagsString());
		return accountSettingRepo.save(setting);
	}


	@Override
	public AccountSetting removeMyTag(Account account, String tagName) {
		AccountSetting setting = accountSettingService.findAccountSettingByAccountId(account.getId());
		String myTagsStr = setting.getMyTags();
		
		MyTagUtils myTagUtil = new MyTagUtils(myTagsStr);
		myTagUtil.removeTag(tagName);
		
		setting.setMyTags(myTagUtil.getTagsString());
		
		accountSettingRepo.save(setting);
		return setting;
	}
	
	@Override
	public List<String> getMyTags(Account account) {
		AccountSetting setting = accountSettingService.findAccountSettingByAccountId(account.getId());
		MyTagUtils mtu = new MyTagUtils(setting.getMyTags());
		return mtu.getMyTagList();
	}

	

	

}
