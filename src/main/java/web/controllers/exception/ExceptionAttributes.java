package web.controllers.exception;

public class ExceptionAttributes {

	private int status;
	private String exception;
	private String message;
	
	public ExceptionAttributes(Exception exception, int httpStatusCode) {
		this.status = httpStatusCode;
		this.exception = exception.getClass().getName();
		this.message = exception.getMessage();
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
