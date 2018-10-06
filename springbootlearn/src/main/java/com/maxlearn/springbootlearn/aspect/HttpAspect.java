package com.maxlearn.springbootlearn.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/*
 * 面向切面，需要提前按照aspectj这个框架，然后在这个添加@Aspect注解
 * @Component是让这个类被Spring框架管理。
 */
@Aspect
@Component
public class  HttpAspect{
	
	
	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

	/*
	 * @Before注解，表示在某个切入点执行之前执行
	 * execution() 切点表达式
	 * 第一个*号表示所有返回类型
	 * 第二个*号表示该包里的所有函数
	 * （..）表示方法里对应的任何参数
	 * 具体语法可以参考这里：Spring Aspect的Execution表达式[https://blog.csdn.net/lang_niu/article/details/51559994]
	 */
	@Pointcut("execution(public * com.maxlearn.springbootlearn.BoysController.*(..))")
	public void log(){
		logger.info("开始 log1");
	}
	
	
	@Before("log()")
	public void before(JoinPoint joinpoint){
		ServletRequestAttributes attributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("前log1");
		//获取URL
		logger.info("url={}", request.getRequestURL());
		//获取方法
		logger.info("method={}", request.getMethod());
		//获取IP
		logger.info("ip={}", request.getRemoteAddr());
		//获取方法名
		logger.info("class_call={}", joinpoint.getSignature().getDeclaringTypeName()+ "." + joinpoint.getSignature().getName());
		//获取参数
		logger.info("args={}", joinpoint.getArgs());
		
	}
	
	@After("log()")
	public void after(){
		logger.info("后log1");
	}
	
	
	@AfterReturning(returning="object",pointcut="log()")
	public void AfterReturn(Object object){
		logger.info("response={}", object.toString());
	}
	
}
