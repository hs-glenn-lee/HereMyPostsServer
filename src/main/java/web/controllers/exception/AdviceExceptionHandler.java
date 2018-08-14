package web.controllers.exception;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import web.exceptions.DeletedException;
import web.exceptions.NotSignedInException;
import web.exceptions.PrivateArticleException;

@ControllerAdvice
public class AdviceExceptionHandler {

	@ExceptionHandler(NotSignedInException.class)
	public @ResponseBody ResponseEntity<?> handleNotSignedInException(HttpServletRequest req, Exception exception) throws JSONException {
		String headerAccept = req.getHeader("Accept");
		
		if(headerAccept != null) {
			if(headerAccept.contains("application/json")) {
				ExceptionAttributes exceptionAttribute = new ExceptionAttributes(exception, HttpStatus.FORBIDDEN.value());
				ResponseEntity<?> respEntity = new ResponseEntity<ExceptionAttributes>(exceptionAttribute, HttpStatus.FORBIDDEN);
				return respEntity;
			}
		}

		String stringContent = "로그인이 필요한 서비스 입니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/exception/not-signed-in");
		
		ResponseEntity<?> respEntity = new ResponseEntity<String>(stringContent, headers, HttpStatus.FOUND);
		return respEntity;

	}
	
	@ExceptionHandler(DeletedException.class)
	public @ResponseBody ResponseEntity<?> handleDeletedException(HttpServletRequest req, Exception exception) throws JSONException {
		String headerAccept = req.getHeader("Accept");
		
		if(headerAccept != null) {
			if(headerAccept.contains("application/json")) {
				ExceptionAttributes exceptionAttribute = new ExceptionAttributes(exception, HttpStatus.FORBIDDEN.value());
				ResponseEntity<?> respEntity = new ResponseEntity<ExceptionAttributes>(exceptionAttribute, HttpStatus.FORBIDDEN);
				return respEntity;
			}
		}

		String stringContent = "삭제된 리소스입니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/exception/deleted-exception");
		
		ResponseEntity<?> respEntity = new ResponseEntity<String>(stringContent, headers, HttpStatus.FOUND);
		return respEntity;

	}
	
	@ExceptionHandler(PrivateArticleException.class)
	public @ResponseBody ResponseEntity<?> handlePrivateArticleException(HttpServletRequest req, Exception exception) throws JSONException {
		System.out.println("PrivateArticleException");
		
		String headerAccept = req.getHeader("Accept");
		
		if(headerAccept != null) {
			if(headerAccept.contains("application/json")) {
				ExceptionAttributes exceptionAttribute = new ExceptionAttributes(exception, HttpStatus.FORBIDDEN.value());
				ResponseEntity<?> respEntity = new ResponseEntity<ExceptionAttributes>(exceptionAttribute, HttpStatus.FORBIDDEN);
				return respEntity;
			}
		}

		String stringContent = "비공개 글 입니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/exception/private-article-exception");
		
		ResponseEntity<?> respEntity = new ResponseEntity<String>(stringContent, headers, HttpStatus.FOUND);
		return respEntity;

	}

}
