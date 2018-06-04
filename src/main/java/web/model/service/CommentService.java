package web.model.service;

import java.util.List;

import web.model.jpa.entities.Comment;

public interface CommentService {
	
	public Comment writeComment(Comment comment);
	
	public List<Comment> getComments(String articleId);
	
	public Comment getComment(Long commentId);
	
}
