package com.maxlearn.springbootlearn;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxlearn.springbootlearn.aspect.HttpAspect;;

@RestController
@RequestMapping("/max")
public class BoysController {
	
	@Autowired
	private BoyRepository boyRepository;
	
		
	@Autowired
	private BoyService boyService;
	
	private final static Logger logger = LoggerFactory.getLogger(BoysController.class);
	
	/*
	 * 返回全部列表
	 */
	@GetMapping(value="/boys")
	public List<Boy> getBoys(){
		return boyRepository.findAll();
	}
	
	/*
	 * 根据id查找记录
	 */
	@GetMapping(value="/boys/{id}")
	public Optional<Boy> getTheBoy(@PathVariable("id") Integer id){
		logger.info("获取Boy列表");
		return boyRepository.findById(id);
	}
		
	/*
	 * 根据id更新记录
	 */
	@PutMapping(value="/boys/{id}")
	public Boy UpdateARecord(@PathVariable("id") Integer myid,
			                 @RequestParam("name") String name,
			                 @RequestParam("height") Integer height){
		
		
		Boy boy = new Boy();
		boy.setId(myid);
		boy.setName(name);
		boy.setHeight(height);
		
		return boyRepository.save(boy);
	}
	
	
	/*
	 * 根据id更新记录
	 * 另一种更完善的写法
	 * 几点注意：
	 * 1. 输入参数，原来使用@RequestParam 以及一个对象属性, 现在直接使用对象本身，springboot框架就自动化将属性值都填好了，
	 * 这样当新增加一个属性值时，用再修改此处代码
	 * 2. 添加@Valid，
	 * @Valid是使用hibernate validation的时候使用，@Validated 是只用spring  Validator 校验机制使用
	 * ，并且需要传入BindingResult对象，用于获取校验失败情况下的反馈信息： 
	 */
	@PutMapping(value="/boys/new/{id}")
	public ResponseResult<Boy> UpdateARecord(@Valid Boy boy, BindingResult bindResult){
		if(bindResult.hasErrors()){
			return ResultUtil.fail(0,bindResult.getFieldError().getDefaultMessage());			
		}
		
//		ResponseResult rr = new ResponseResult();
//		rr.setCode(0);
//		rr.setMsg("成功");
//		rr.setData(boy);
		
			
		return ResultUtil.success(boy);
	}
	
	
	/* 
	 * 删除一条记录
	 */
	@DeleteMapping(value="/boys/{id}")
	public void DeleteARecorde(@PathVariable("id") Integer Id){
		boyRepository.deleteById(Id);
	}
	
	@PostMapping(value="/addBoy")
	public Boy addBoy(@RequestParam("name") String name,
					  @RequestParam("height") Integer height){
		Boy boy = new Boy();
		boy.setName(name);
		boy.setHeight(height);
		return boyRepository.save(boy);
		
	}
	
	@GetMapping(value="/addtwo")
	public void addTwo(){
		boyService.AddTwo();
	}
	
	@GetMapping(value="/findhigh/{id}")
	public void findhigh(@PathVariable("id") Integer myid) throws Exception{
		boyService.getHeight(myid);
	}
}
