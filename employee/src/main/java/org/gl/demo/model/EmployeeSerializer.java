package org.gl.demo.model;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeSerializer implements Serializer<Employee>{

	@Override
	public byte[] serialize(String topic, Employee employee) {
		
	    byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(employee).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	}
	

}
