package org.gl.demo.model;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeDeserializer implements Deserializer<Employee>{
	
	
	public EmployeeDeserializer() {
		
	}


	@Override
	public Employee deserialize(String topic, byte[] data) {
		
	    ObjectMapper mapper = new ObjectMapper();
	    Employee employee = null;
	    try {
	      employee = mapper.readValue(data, Employee.class);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return employee;

	}


}
