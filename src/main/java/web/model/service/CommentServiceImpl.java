package web.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Comment;
import web.model.jpa.repos.AccountRepo;
import web.model.jpa.repos.CommentRepo;
import web.model.service.sign.SignService;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	SignService signService;
	
	@Autowired
	EntityManager em;
	
	@Transactional
	@Override
	public Comment writeComment(Comment comment) {
		/*
		 * 
		 * 버그? save 한 객체를 그대로 캐쉬에 넣고, 그 객체를 갖고오는듯
		 * getAuhtor가 jpa 객체가 아니라서 getAuthor().getAccountSetting() 이 null이 되는 거 같다.
		 * 
		 * comment = commentRepo.saveAndFlush(comment);
		Comment ret = commentRepo.findOne(comment.getId());
		
		if(!comment.getIsAnonymous()) {
			System.out.println("wefwef");
			ret.getAuthor();
			AccountSetting set = ret.getAuthor().getAccountSetting();
			
			System.out.println(ret);
			System.out.println(set);//null
			
			
			Account test = accountRepo.findOne(ret.getAuthor().getId());
			AccountSetting test2 = test.getAccountSetting();
			System.out.println(test2);//not null
		}
		return comment;*/
		
		
		comment = commentRepo.saveAndFlush(comment);
		em.clear();
		comment = commentRepo.findOneWithAuthorAndAccountSetting(comment.getId());
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

	@Override
	public Comment deleteComment(Comment comment, HttpSession httpSession) throws NotSignedInException {
		Comment target = commentRepo.findOne(comment.getId());
		if(target==null) {
			throw new IllegalStateException("Comment Not Found.");
		}

		if(target.getIsAnonymous()) {
			if(target.getAnonymousPassword() == comment.getAnonymousPassword()) {
				target.setIsDel(true);
				commentRepo.saveAndFlush(target);
				return target;
			}else {
				throw new IllegalStateException("Wrong Password.");
			}
		}else {
			if(target.getAuthor().getId() == signService.getSign(httpSession).getAccount().getId()) {
				target.setIsDel(true);
				commentRepo.saveAndFlush(target);
				target.getAuthor().getAccountSetting();
				return target;
			}else {
				throw new IllegalStateException("Unauthorized.");
			}
		}

	}

	

}
