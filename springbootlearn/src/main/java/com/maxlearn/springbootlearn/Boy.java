package com.maxlearn.springbootlearn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;

@Entity
public class Boy {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	
	@Min(value=170, message="个子太矮")
	private Integer height;
	
	
	public Boy(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	@Override
	public String toString(){
		return "id:" + this.id + ";" + "name:" + this.name + ";" + "height:" + this.height + "."; 
	}
}
