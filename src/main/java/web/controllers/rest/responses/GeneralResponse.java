package web.controllers.rest.responses;

public class GeneralResponse<T> {
	
	public static final String RESULT_FAIL = "FAIL";
	public static final String RESULT_SUCCESS = "SUCCESS";
	
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
	public T getResponseData() {
		return responseData;
	}
	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}
	
	public static GeneralResponse<Object> createSUCCESSResponse() {
		GeneralResponse<Object> successResponse = new GeneralResponse<Object>();
		successResponse.setResult(GeneralResponse.RESULT_SUCCESS);
		return successResponse;
	}
	
	public static GeneralResponse<Object> createFAILResponse() {
		GeneralResponse<Object> failResponse = new GeneralResponse<Object>();
		failResponse.setResult(GeneralResponse.RESULT_FAIL);
		return failResponse;
	}
}
