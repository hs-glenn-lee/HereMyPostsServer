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
import web.model.jpa.entities.Tag;
import web.model.jpa.entities.TagArticle;
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
	SignService signService;
	
	@Override
	public List<Tag> getSeriesByUsername(String username) {
		/*Query query = em.createQuery(
		"SELECT series from Series series"
		+ "join fetch Account acc"
		+ "where acc.username = :username");
		query.setParameter("username", username);
		
		List<Series> series = null;
		series = query.getResultList();*/
		
		return tagRepo.findByOwnerUsername(username);
	}
	
	
	
	
	@Override
	public List<Article> getArticlesBySeriesId(String tagId) {
		Query query = em.createQuery(
				"SELECT articles from Article articles"
				+ "join fetch TagArticle ta"
				+ "join fetch Tag tag"
				+ "where tag.id = :tagId");
		
		query.setParameter("tagId", tagId);
		
		List<Article> articles = null;
		articles = query.getResultList();
		
		return articles;

	}
	

	@Override
	public Tag saveSeries(Tag series) {
		em.persist(series);
		em.close();
		return series;
	}
	
	@Transactional
	@Override
	public Tag createNewSeries(Tag series) {
		series.setId(UUIDUtil.getUUID());
		em.persist(series);
		em.close();
		return series;
	}
	
	
	@Override
	public Tag saveSeries(Account owner, Tag series) {
		owner = em.find(Account.class, owner.getId());
		em.persist(owner);

		series.setOwner(owner);
		em.persist(series);
		
		em.close();
		return series;
	}
	
	
	@Override
	public void addSeriesArticle(Article article, Tag series) {
		
	}
	

	@Override
	public Tag getSeries(String seriesId) {
		Tag series = em.find(Tag.class, seriesId);
		return series;
	}
	
	

	@Override
	public List<Article> getArticlesOfSeries(Tag series) {
		List<TagArticle> sa = em.find(Tag.class, series.getId()).getTagArticles();
		
		
		return null;
	}




	@Override
	public List<Tag> findMySeriesByPage(HttpSession session, Pageable pageable) throws NotSignedInException {
		Account me = signService.getSign(session).getAccount();
		List<Tag> seriesList = tagRepo.findMyTagsByPage(me.getId(), pageable);
		return seriesList;
	}




	@Override
	public List<Tag> findAllMySeries(HttpSession session) throws NotSignedInException {
		Account me = signService.getSign(session).getAccount();
		List<Tag> seriesList = tagRepo.findAllMyTags(me.getId());
		return seriesList;
	}

}
