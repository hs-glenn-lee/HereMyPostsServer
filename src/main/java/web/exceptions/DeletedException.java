package web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * this exception occurs when user access to end point
 * which is not permitted without signed-in session status. 
 * */
@ResponseStatus(value=HttpStatus.FORBIDDEN)
public class DeletedException extends Exception{

	private static final long serialVersionUID = -4081398255688059175L;
	
	public DeletedException() {
		super("삭제된 리소스입니다.");
	}

}
