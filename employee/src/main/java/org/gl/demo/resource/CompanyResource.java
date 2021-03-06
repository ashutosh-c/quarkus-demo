package org.gl.demo.resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.gl.demo.model.Employee;
import org.gl.demo.repository.EmployeeRepository;
import org.gl.demo.sevice.EmployeeService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;

import io.smallrye.reactive.messaging.annotations.Channel;



@Path("/company")
public class CompanyResource {

	    @Autowired
	    private EmployeeRepository empRepository;
	    
	    @Autowired
	    private EmployeeService employeeService;
	    
	    
		@Inject
		private List<Employee> employeeList;


	
	    @POST
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Employee addEmployee(Employee employee) {
	        return empRepository.save(employee);
	    }

	    
	    @GET
		@Produces(MediaType.APPLICATION_JSON)
	    public List<Employee> getAllEmployees() {
	    	employeeService.setTaskNeeded(true);
	    	return employeeList;
		}
		

		// @GET
		// @Produces(MediaType.APPLICATION_JSON)
		// @Path("/emp")
	    // public List<Employee> readTopic() {
		// 	List<Employee> list = new ArrayList<>();
		// //	incomingStream.toString();
		// 	ReactiveStreams.fromPublisher(incomingStream).map(i->list.addAll(i));

			
		// //ReactiveStreams.fromPublisher(incomingStream).flatMapIterable(i->list);
		//  // return ReactiveStreams.fromPublisher(incomingStream).collect(Collectors.toList());
        //  return list;

	    // }

	    
	    
	    
	    @DELETE
	    @Produces(MediaType.TEXT_PLAIN)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public String deleteEmployee(Employee employee) {
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
