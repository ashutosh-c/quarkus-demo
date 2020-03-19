package org.gl.demo.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gl.demo.model.Employee;
import org.gl.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;



@Path("/company")
public class CompanyResource {
	
	
	    @Autowired
	    private EmployeeRepository empRepository;
	
	    @POST
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Employee addEmployee(Employee employee) {
	        return empRepository.save(employee);
	    }
	    
	    
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public Iterable<Employee> getAllEmployees() {
	        return empRepository.findAll();
	    }
	    
	    
	    @DELETE
	    @Produces(MediaType.TEXT_PLAIN)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public String deleteEmployee(Employee employee) {
	         Employee persistedEmployee = empRepository.findById(employee.getId()).get();
	         empRepository.deleteById(employee.getId());		 
	         return "successfuly Deleted";
	    }
	    
	    
	    @PUT
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Employee updateEmployee(Employee employee) {
	        return empRepository.save(employee);
	    }


}
