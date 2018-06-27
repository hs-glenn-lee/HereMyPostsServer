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
	public List<TagArticle> updateTagsArticlesOfArticle(String targetArticleId, List<TagArticle> tagsArticles);
	public List<TagArticle> findTagArticlesByArticleId(String articleId);
	
	//my tags
	public AccountSetting addMyTags(AccountSetting setting, List<Tag> tags);
	public AccountSetting removeMyTag(Account account, String tagName);
	public List<String> getMyTags(Account account);
	
}
