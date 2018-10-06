package com.maxlearn.springbootlearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoyService {

	@Autowired
	private BoyRepository boyRepository;
	
	@Transactional
	public void AddTwo(){
		Boy boy1 = new Boy();
		boy1.setName("boy1");
		boy1.setHeight(157);
		boyRepository.save(boy1);
		
		
		Boy boy2 = new Boy();
		boy2.setName("boy2");
		boy2.setHeight(167);
		boyRepository.save(boy2);
				
	}
	
	public void getHeight(Integer id) throws Exception{
		Boy boy = boyRepository.findById(id).get();
		Integer height = boy.getHeight();
		if(height < 120){
			throw new Exception("不足120cm，免费");
		}else if (height > 120 && height < 150){
			throw new Exception("超过120，但矮与150，不足");
		}
		
		
	}
}
