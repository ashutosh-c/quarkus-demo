package org.gl.demo.sevice;

import java.util.ArrayList;
import java.util.List;
import org.gl.demo.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanCreator {
	
	@Bean
	public List<Employee> employeeList(){
		
		return new ArrayList<Employee>();
	}

}
