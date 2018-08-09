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
		comment = commentRepo.saveAndFlush(comment);
		return comment;
	}

	@Override
	public List<Comment> getCommentsByArticleId(String articleId) {
		List<Comment> ret = commentRepo.findByArticleId(articleId);
		for(Comment c : ret) {
			if(!c.getIsAnonymous()) {
				c.getAuthor().setPassword(null);
			}
		}
		return ret;
	}
	
	@Override
	public Long getCountCommentsByArticleId(String articleId) {
		Long count = commentRepo.findCountCommentsByArticleId(articleId);
		return count;
	}
	

	@Override
	public Comment getComment(Long commentId) {
		return commentRepo.getOne(commentId);
	}

	

}
