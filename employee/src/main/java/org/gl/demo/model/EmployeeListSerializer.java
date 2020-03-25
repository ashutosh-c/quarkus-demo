package org.gl.demo.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.Serializer;

public class EmployeeListSerializer implements Serializer<Iterable<Employee>>{

	@Override
	public byte[] serialize(String topic, Iterable<Employee> data) {

	    byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(data).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	}

}
