package com.maxlearn.springbootlearn;

public class ResultUtil {

	public static ResponseResult success(Object object){
		ResponseResult rr = new ResponseResult();
		rr.setCode(0);
		rr.setMsg("成功");
		rr.setData(object);
		return rr;
	}
	
	public static ResponseResult fail(Integer code, String msg){
		ResponseResult rr = new ResponseResult();
		rr.setCode(code);
		rr.setMsg(msg);
		rr.setData(null);
		return rr;
	}
}
