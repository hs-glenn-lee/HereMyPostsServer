package web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * this exception occurs when user access to end point
 * which is not permitted without signed-in session status. 
 * */
@ResponseStatus(value=HttpStatus.FORBIDDEN)
public class NotSignedInException extends Exception{

	private static final long serialVersionUID = 7500096759685457171L;

	public NotSignedInException() {
		super("로그인이 필요한 서비스입니다. 로그인 해주세요.");
	}

}