package web.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Article;
import web.model.jpa.entities.Series;
import web.model.jpa.entities.SeriesArticle;
import web.model.jpa.repos.SeriesRepo;
import web.model.service.sign.SignService;
import web.utils.UUIDUtil;

@Service("seriesService")
public class SeriesServiceImpl  implements SeriesService{
	
	@Autowired
	EntityManager em;
	
	@Autowired
	SeriesRepo seriesRepo;
	
	@Autowired
	SignService signService;
	
	@Override
	public List<Series> getSeriesByUsername(String username) {
		/*Query query = em.createQuery(
		"SELECT series from Series series"
		+ "join fetch Account acc"
		+ "where acc.username = :username");
		query.setParameter("username", username);
		
		List<Series> series = null;
		series = query.getResultList();*/
		
		return seriesRepo.findByOwnerUsername(username);
	}
	
	
	
	
	@Override
	public List<Article> getArticlesBySeriesId(String seriesId) {
		Query query = em.createQuery(
				"SELECT articles from Article articles"
				+ "join fetch SeriesArticle sa"
				+ "join fetch Series s"
				+ "where s.id = :seriesId");
		
		query.setParameter("seriesId", seriesId);
		
		List<Article> articles = null;
		articles = query.getResultList();
		
		return articles;

	}
	

	@Override
	public Series saveSeries(Series series) {
		em.persist(series);
		em.close();
		return series;
	}
	
	@Transactional
	@Override
	public Series createNewSeries(Series series) {
		series.setId(UUIDUtil.getUUID());
		em.persist(series);
		em.close();
		return series;
	}
	
	
	@Override
	public Series saveSeries(Account owner, Series series) {
		owner = em.find(Account.class, owner.getId());
		em.persist(owner);

		series.setOwner(owner);
		em.persist(series);
		
		em.close();
		return series;
	}
	
	
	@Override
	public void addSeriesArticle(Article article, Series series) {
		
	}
	

	@Override
	public Series getSeries(String seriesId) {
		Series series = em.find(Series.class, seriesId);
		return series;
	}
	
	

	@Override
	public List<Article> getArticlesOfSeries(Series series) {
		List<SeriesArticle> sa = em.find(Series.class, series.getId()).getSeriesArticles();
		
		
		return null;
	}




	@Override
	public List<Series> findMySeriesByPage(HttpSession session, Pageable pageable) throws NotSignedInException {
		Account me = signService.getSign(session).getAccount();
		List<Series> seriesList = seriesRepo.findMySeriesByPage(me.getId(), pageable);
		return seriesList;
	}

}
