package org.gl.consumer.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeListDeserializer implements Deserializer<Iterable<Map>>{
	
	


	@Override
	public List<Map> deserialize(String topic, byte[] data) {
		
		ObjectMapper mapper = new ObjectMapper();
		List<Map> employeeList = new ArrayList<>();
	    Iterable iterable = null;
	    try {
			iterable = mapper.readValue(data, Iterable.class);	
			
			//Map<String,Employee> map = (Map<String,Employee>)iterable.iterator().next();
			Iterator<Map> iterator = iterable.iterator();
			
			while(iterator.hasNext()) {
				Map<String,Object> map = iterator.next();
			for(Map.Entry<String,Object> entry: map.entrySet()) {
				
				System.out.println("**********************************************Key="+entry.getKey());
				System.out.println("**********************************************VALUE="+entry.getValue());


			  }
			}
			
			
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return employeeList;

	}


}
