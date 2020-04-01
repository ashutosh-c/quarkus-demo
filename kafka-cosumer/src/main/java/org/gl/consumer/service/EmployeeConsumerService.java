package org.gl.consumer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.gl.consumer.model.Employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class EmployeeConsumerService{



    
    
    public List consume() {   	
    	
    	   Properties props = new Properties();
    	   props.put("bootstrap.servers", "localhost:9092");
    	   props.put("group.id", "employeeConsumer5");
    	     props.put("enable.auto.commit", "false");
    	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    	     consumer.subscribe(Arrays.asList("employeeTopic3"));
//    	     TopicPartition tp = new TopicPartition("employeeTopic2", 0);
//    	     List<TopicPartition> tps = Arrays.asList(tp);
//    	     consumer.assign(tps);

    	     
    	     final int minBatchSize = 200;
    	     List <List<Employee>> listOfList = new ArrayList<>();
    	        ConsumerRecords records = consumer.poll(100);
    	 		System.out.println("*********************************************************TOTAL RECORDS ="+records.count());
    	 		System.out.println("#########################################################RECORDS#################################");

    	 		Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
    	       
    	 		
    	 		while (iterator.hasNext()) {
    	 			    ConsumerRecord<String, String> record = iterator.next();
    	    	 		System.out.println("*********************************************************KEY="+record.key());
    	    	 		System.out.println("*********************************************************VALUE="+record.value());
                        String value = record.value();
                        List<Employee> list = convertJsonToObject(value);
    	    	 		listOfList.add(list);
    	         }
    	              consumer.commitSync();
    	              consumer.close();
				return listOfList;    	
    }
    
    
    private List<Employee> convertJsonToObject(String str){
    	
    	ObjectMapper mapper = new ObjectMapper();
    	List<Employee> empList = null;
    	try {
    	   empList = mapper.readValue(str, new TypeReference<List<Employee>>(){});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

    	return empList;
    	
    }
    
    
    
    
    
    



    }












