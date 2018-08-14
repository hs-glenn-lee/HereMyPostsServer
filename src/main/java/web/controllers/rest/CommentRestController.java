package web.controllers.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import web.controllers.rest.responses.CommentAsResponse;
import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Comment;
import web.model.service.CommentService;


@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommentRestController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value="/comment/{commentId}", method=RequestMethod.GET)
	public Comment getComment(@PathVariable String commentId) throws IOException {
		Long _commentId = Long.parseLong(commentId);
		return commentService.getComment(_commentId);
		
	}
	
	
	@RequestMapping(value="/comment/write", method=RequestMethod.PUT)
	public CommentAsResponse writeComment(HttpServletRequest req, @RequestBody Comment comment) throws IOException {
		Comment written = commentService.writeComment(comment);
		written.toString();
		CommentAsResponse cr = new CommentAsResponse(written);
		return cr;
	}
	
	@RequestMapping(value="/comment/delete", method=RequestMethod.POST)
	public CommentAsResponse deleteComment(HttpServletRequest req,
			@RequestBody Comment comment) throws IOException, NotSignedInException {
		Comment deleted = commentService.deleteComment(comment, req.getSession());
		return new CommentAsResponse(deleted);
	}
	
	@RequestMapping(value="/article/{articleId}/comments", method=RequestMethod.GET)
	public List<CommentAsResponse> getCommentsOfArticle(HttpServletRequest req, @PathVariable String articleId) {
		List<Comment> commments = commentService.getCommentsByArticleId(articleId);
		List<CommentAsResponse> ret = new ArrayList<CommentAsResponse>();
		for(Comment c : commments) {
			ret.add(new CommentAsResponse(c));
		}
		
		return ret;
		
	}
	
	@RequestMapping(value="/article/{articleId}/comments/count", method=RequestMethod.GET)
	public HashMap<String, Long> getCountCommentsOfArticle(HttpServletRequest req, @PathVariable String articleId) {
		Long count = commentService.getCountCommentsByArticleId(articleId);
		HashMap<String, Long> res = new HashMap<String, Long> ();
		res.put("count", count);
		return res;
		
	}

}
