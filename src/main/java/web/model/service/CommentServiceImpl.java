package web.model.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Comment;
import web.model.jpa.repos.CommentRepo;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	EntityManager em;
	
	@Transactional
	@Override
	public Comment writeComment(Comment comment) {
		em.persist(comment);
		em.flush();
		em.close();
		return comment;
	}

	@Override
	public List<Comment> getComments(String articleId) {
		/*Article article = em.find(Article.class, articleId);//todo is comments has article??
		article.getComments();
		System.out.println(article.getComments().size());*/
		
		Query query = em.createQuery("SELECT c from Comment c join fetch c.article ca"
				+ "	where ca.id = :articleId ");
		query.setParameter("articleId", articleId);
		
		List<Comment> comments = query.getResultList();
		em.close();
		return comments;
	}

	@Override
	public Comment getComment(Long commentId) {
		return commentRepo.getOne(commentId);
	}

}
