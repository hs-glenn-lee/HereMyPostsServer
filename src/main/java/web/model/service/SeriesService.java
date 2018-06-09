package web.model.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Series;

public interface SeriesService{
	
	public List<Series> getSeriesByUsername(String username);
	
	public Series saveSeries(Series series);
	public Series saveSeries(Account account, Series series);
	public Series getSeries(String seriesId);
	
	public List<Article> getArticlesOfSeries(Series series);
	public void addSeriesArticle(Article article, Series series);
	Series createNewSeries(Series series);

	List<Article> getArticlesBySeriesId(String seriesId);
	
	public List<Series> findMySeriesByPage(HttpSession session, Pageable pageable) throws NotSignedInException;
	
	
}
