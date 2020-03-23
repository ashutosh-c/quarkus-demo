package org.gl.demo.sevice;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.gl.demo.model.Employee;

import io.quarkus.arc.DefaultBean;

@ApplicationScoped
public class BeanCreator {
	
	@Produces
    @DefaultBean
	public List<Employee> employeeList(){
		
		return new ArrayList<Employee>();
	}

}
