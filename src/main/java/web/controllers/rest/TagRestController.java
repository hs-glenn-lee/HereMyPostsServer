package web.controllers.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import web.controllers.rest.responses.GenericResponse;
import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Tag;
import web.model.jpa.entities.TagArticle;
import web.model.service.TagService;
import web.model.service.sign.SignService;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TagRestController {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	SignService signService;
	
	@RequestMapping(value="/tag/myTags", method=RequestMethod.GET)
	public List<String> getMyTags(HttpServletRequest req) throws NotSignedInException {
		Account me = signService.getSign(req.getSession()).getAccount();
		return tagService.getMyTags(me);
	}
	
	@RequestMapping(value="/article/{articleId}/tags", method=RequestMethod.GET)
	public List<Tag> getArticleTags(HttpServletRequest req,
										@PathVariable("articleId") String articleId) {
		List<Tag> tags = tagService.findTagsByArticle(articleId);
		
		
		return tags;
	}
	
	@RequestMapping(value="/tag/addToArticle", method=RequestMethod.PUT)
	public TagArticle addTagToArticle(HttpServletRequest req,
								@RequestBody TagArticle ta) {
		return tagService.addTagToArticle(ta);
	}
	
	@RequestMapping(value="/tag/removeFromArticle", method=RequestMethod.POST)
	public GenericResponse<?> removeTagFromArticle(HttpServletRequest req,
							@RequestBody TagArticle ta) {
		try {
			tagService.removeTagFromArticle(ta.getTag(), ta.getArticle());
			GenericResponse<?> gr = new GenericResponse<Object>();
			return gr;
		}catch(Exception e) {
			GenericResponse<?> gr = new GenericResponse<Object>();
			gr.setStatus(GenericResponse.STATUS_FAIL);
			gr.setMessage(e.getMessage());
			return gr;
		}
	}

	
	
}
