package com.maxlearn.springbootlearn;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BoyRepository extends JpaRepository<Boy, Integer> {
	
}
