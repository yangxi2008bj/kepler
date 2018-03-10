package com.lachesis.windranger.common.exception;

/*@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "找不到对应的资源")*/
public class RfNotFoundException extends RfException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RfNotFoundException(String msg)
	{
		super(msg);
	}

	public RfNotFoundException(String format, Object... args) {

		super(format, args);
	}

	public RfNotFoundException(String entityName, String conditionName, String condition)
	{
		super(String.format("δ�ҵ�ָ����%s(%s:%s)", entityName, conditionName, condition));
	}
}