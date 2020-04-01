package org.gl.consumer.model;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

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
