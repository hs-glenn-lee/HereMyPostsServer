package web.model.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Comment;

public interface CommentService {
	
	public Comment writeComment(Comment comment);
	
	public Comment deleteComment(Comment comment, HttpSession httpSession) throws NotSignedInException;
	
	public List<Comment> getCommentsByArticleId(String articleId);
	
	public Long getCountCommentsByArticleId(String articleId);
	
	public Comment getComment(Long commentId);
	
}
