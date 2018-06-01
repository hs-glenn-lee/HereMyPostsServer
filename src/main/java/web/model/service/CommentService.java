package web.model.service;

import java.util.Set;

import web.model.jpa.entities.Comment;

public interface CommentService {
	
	public Comment writeComment(Comment comment);
	
	public Set<Comment> getComments(String articleId);
	
	public Comment getComment(Long commentId);
	
}
