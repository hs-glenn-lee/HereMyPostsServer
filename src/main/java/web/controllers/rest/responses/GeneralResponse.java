package web.controllers.rest.responses;

public class GeneralResponse<T> {
	
	public static final String RESULT_FAIL = "FAIL";
	public static final String RESULT_SUCESS = "SUCCESS";
	
	private String result;
	private String message;
	private T responseData;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getT() {
		return responseData;
	}
	public void setT(T t) {
		this.responseData = t;
	}
	
}
