package com.lachesis.windranger.common.exception;

public abstract class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppException(String msg)
	{
		super(msg);
	}
	
	public AppException(String format, Object... args) {
		
		super(String.format(format, args));
	}
	
	
	public static String getExceptionJsonString(Exception ex, String statusName, int statusCode) {
		
//		AppExceptionJsonContainer container = new AppExceptionJsonContainer(
//				String.format("%d", statusCode),
//				ex.getMessage(),
//				ex.getMessage());
//
//		StackTraceElement[] elements = ex.getStackTrace();
//		for(StackTraceElement element : elements) {
//			container.getStackTraces().add(element.toString());
//		}
//		String responseContent = JSON.toJSONString(container);
//		return responseContent;
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\"code\":").append(statusCode).append(",\"message\":\"").append(ex.getMessage()).append("\"}");
		return buffer.toString();
		
		
	} 
	
}
