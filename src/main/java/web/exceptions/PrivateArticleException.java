package web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * this exception occurs when user access to end point
 * which is not permitted without signed-in session status. 
 * */
@ResponseStatus(value=HttpStatus.FORBIDDEN)
public class PrivateArticleException extends Exception{

	private static final long serialVersionUID = 5882546160776834804L;

	public PrivateArticleException() {
		super("비공개 글입니다. 조회할 수 없습니다.");
	}

}