package com.maxlearn.springbootlearn;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 该类用于全局处理异常，不用将每个异常处理写在不同的地方
 * 
 * 主要的注解：
 *@ControllerAdvice 注解定义全局异常处理类，确保类能被扫描到并装载进 Spring 容器中。
 *@ExceptionHandler 注解声明异常处理方法，就会处理所有 Controller 层抛出的 Exception 及其子类的异常，这是最基本的用法了。
 *@ResponseBody 将方法的返回值处理成jason格式
 *
 *具体参考：【https://blog.csdn.net/kinginblue/article/details/70186586】
 * 
 */
@ControllerAdvice
public class ExceptionHandle {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseResult handle(Exception e){		
		return ResultUtil.fail(1, e.getMessage());
	}
}
