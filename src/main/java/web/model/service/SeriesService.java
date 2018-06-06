package web.model.service;

import java.util.List;
import java.util.Set;

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
	
	
}
