package com.uusoft.atp.utils;

/**
 * 
 * 类描述：
 *   结果返回类
 * 
 * @author 周辉
 * @company:上海投投金融有限责任公司
 * @email zhouhui@66money.com
 * @since 2016年4月19日 上午10:33:00 
 * @version V1.0
 */
public class ResultTool<T>
{	
    /**
     * 成功返回码"0000"
     */
    private static final String SUCCRESS_CODE = "0000";
    private static final String SUCCRESS_MSG = "操作成功";
    
	private String code;
	private String message;
	private T obj;
	
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public T getObj()
    {
        return obj;
    }
    public void setObj(T obj)
    {
        this.obj = obj;
    }
    
    public static <T> ResultTool<T> setResult(String code,String message,T obj)
    {
    	return new ResultTool<T>(code,message,obj);
    }
	public ResultTool(String code, String message, T obj) {
		super();
		this.code = code;
		this.message = message;
		this.obj = obj;
	}
	
	public boolean isSuccess() {
	    return SUCCRESS_CODE.equals(code);
	}
	
	public static <T> ResultTool<T> success(T obj) {
	    return new ResultTool<T>(SUCCRESS_CODE, SUCCRESS_MSG, obj);
	}
	@Override
	public String toString() {
		return "{code=" + code + ", message=" + message + ", obj=" + obj + "}";
	}
	
 }
