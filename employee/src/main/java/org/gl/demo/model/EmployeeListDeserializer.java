package org.gl.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.Deserializer;

public class EmployeeListDeserializer implements Deserializer<Iterable<Employee>>{

	@Override
	public List<Employee> deserialize(String topic, byte[] data) {

		ObjectMapper mapper = new ObjectMapper();
		List<Employee> employeeList = new ArrayList<>();
	    Iterable iterable = null;
	    try {
			iterable = mapper.readValue(data, Iterable.class);			
			for(Object object : iterable){
				Employee employee = (Employee)object;
				employeeList.add(employee);
			}
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return employeeList;

	}

}
