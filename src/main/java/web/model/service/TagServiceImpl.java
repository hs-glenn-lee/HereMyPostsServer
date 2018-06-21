package web.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.AccountSetting;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Tag;
import web.model.jpa.entities.TagArticle;
import web.model.jpa.repos.AccountSettingRepo;
import web.model.jpa.repos.TagArticleRepo;
import web.model.jpa.repos.TagRepo;
import web.model.service.sign.SignService;
import web.utils.UUIDUtil;

@Service("seriesService")
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
	

/*	@Override
	public List<Tag> findTagsByOwner(Account owner) {
		List<Tag> tags = tagRepo.findByOwnerId(owner.getId());
		return tags;
	}*/

	@Override
	public List<Tag> findTagsByArticle(String articleId) {
		List<Tag> tags = tagRepo.findByArticleId(articleId);
		return tags;
	}

	@Override
	public TagArticle addTagToArticle(Tag tag, Article article) {
		TagArticle ta = new TagArticle(tag, article);
		ta.setId(UUIDUtil.getUUID());
		
		tag = tagRepo.save(tag);
		ta = tagArticleRepo.save(ta);
		return ta;
	}
	
	@Override
	public TagArticle addTagToArticle(TagArticle ta) {
		ta.setId(UUIDUtil.getUUID());
		Tag tag = tagRepo.save(ta.getTag());
		ta = tagArticleRepo.save(ta);
		return ta;
	}
	

	@Override
	public void removeTagFromArticle(Tag tag, Article article) {
		/*List<TagArticle> tas = tagArticleRepo.findByTagId(tag.getId());
		if ( tas.isEmpty() ) {
			throw new IllegalStateException("there\' no matched tag or article");
		}else if(tas.size() == 1){//if this tag is being tagged at only one article, delete tagArticle and also tag
			TagArticle ta = tas.get(0);
			Tag ta_tag = ta.getTag();
			tagArticleRepo.delete(ta);
			tagRepo.delete(ta_tag);
		}else {//if this tag is being tagged at more than one article, delete tagArticle only
			for(TagArticle ta : tas) {
				if(ta.getArticle().equalsWithId(article)) {
					tagArticleRepo.delete(ta);
					break;
				}
			}
		}*/
	}

	@Override
	public List<TagArticle> addTagsToArticle(List<TagArticle> tas) {
		return tagArticleRepo.save(tas);
	}

	@Override
	public List<Tag> saveTags(List<Tag> tags) {//TODO performance
		return tagRepo.save(tags);
	}

	@Override
	public AccountSetting addMyTags(AccountSetting setting, List<Tag> tags) {
		MyTagUtils myTagUtil = new MyTagUtils(setting.getMyTags());
		myTagUtil.addTags(tags);
		
		setting.setMyTags(myTagUtil.getTagsString());
		
		accountSettingRepo.save(setting);
		return setting;
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

}
