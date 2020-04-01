package org.gl.consumer.resource;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gl.consumer.model.Employee;
import org.gl.consumer.service.EmployeeConsumerService;

@ApplicationScoped
@Path("/consumer")
public class EmployeeConsumerResource {
	
	@Inject
	EmployeeConsumerService service;
	

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<List<Employee>> getEmployeeDetails() {
        return service.consume();
    }
}