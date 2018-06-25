package web.model.service;

import java.util.ArrayList;
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
	public List<TagArticle> saveTagsArticles(List<TagArticle> tagsArticles) {
		
		AccountSetting authorSetting = tagsArticles.get(0).getArticle().getAuthor().getAccountSetting();
		Article article = tagsArticles.get(0).getArticle();
		System.out.println("saveTagsArticles");
		System.out.println(article);
		
		article = articleRepo.findOne(article.getId());
		
		List<Tag> tags = new ArrayList<Tag>();
		for(TagArticle ta : tagsArticles) {
			tags.add(ta.getTag());
			if(ta.getId() == null) {
				ta.setId(UUIDUtil.getUUID());
			}
			if( !article.getId().equals( ta.getArticle().getId() ) ) {
				throw new IllegalStateException("TagsArticles\'s article must be eqauls.");
			}else {
				ta.setArticle(article);
			}
		}
		tagRepo.save(tags);
		
		List<TagArticle> ret = tagArticleRepo.save(tagsArticles);
		
		this.addMyTags(authorSetting, tags);
		
		return ret;
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
