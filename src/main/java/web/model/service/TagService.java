package web.model.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Tag;

public interface TagService{
	
	public List<Tag> getSeriesByUsername(String username);
	
	public Tag saveSeries(Tag series);
	public Tag saveSeries(Account account, Tag series);
	public Tag getSeries(String seriesId);
	
	public List<Article> getArticlesOfSeries(Tag series);
	public void addSeriesArticle(Article article, Tag series);
	Tag createNewSeries(Tag series);

	List<Article> getArticlesBySeriesId(String seriesId);
	
	public List<Tag> findMySeriesByPage(HttpSession session, Pageable pageable) throws NotSignedInException;
	public List<Tag> findAllMySeries(HttpSession session) throws NotSignedInException;
	
}
