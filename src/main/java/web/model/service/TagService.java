package web.model.service;

import java.util.List;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.AccountSetting;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Tag;
import web.model.jpa.entities.TagArticle;

public interface TagService{
	
	//tag
	public List<Tag> findTagsByArticle(String articleId);
	public List<Tag> saveTags(List<Tag> tags);

	//tagArticle
	public TagArticle addTagToArticle(Tag tag, Article article);
	public TagArticle addTagToArticle(TagArticle ta);
	public List<TagArticle> addTagsToArticle(List<TagArticle> tas);
	public void removeTagFromArticle(Tag tag, Article article);
	
	//my tags
	public AccountSetting addMyTags(AccountSetting setting, List<Tag> tags);
	public AccountSetting removeMyTag(Account account, String tagName);
	
}
