package org.gl.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test {
	
	@Bean
	public List<Employee> list(){
		return new ArrayList<Employee>();
	}

}
