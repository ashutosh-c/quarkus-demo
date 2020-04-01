package org.gl.demo.sevice;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.gl.demo.model.Employee;
import org.gl.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;

@ApplicationScoped
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;
	
	@Inject
	private List<Employee> employeeList;
	
	private Boolean isTaskNeeded=false;

	
	@Outgoing("employee-channel")
	public Flowable<KafkaMessage<String,String>> pushInTopic2() {

		return Flowable.interval(500, TimeUnit.MILLISECONDS).onBackpressureDrop().map(tick -> {
			
			Iterable<Employee> iterable = getAllEmployeeFromDB();
			employeeList.clear();			
	  	   for(Employee employee : iterable) {
			employeeList.add(employee);
		   }
			while(!this.isTaskNeeded) {
				
			}
			this.isTaskNeeded=false;
			String itearbleStr = convertObjectToString(iterable);
	  	 return KafkaMessage.of(LocalDate.now().toString(), itearbleStr);
	  	 
		});

	}


	private String convertObjectToString(Iterable<Employee> iterable){

		  ObjectMapper mapper = new ObjectMapper();
		  String iterableStr=null ;
		  try{
			iterableStr = mapper.writeValueAsString(iterable);

		  }catch(Exception e){
			e.printStackTrace();
		  }
			return iterableStr;
	}


	@Incoming("employeeTopic")
	@Outgoing("incomingStream")
	@Broadcast
	public List<Employee> getFromTopic(List<Employee> employeeList) {

		return employeeList;
	}

	public Iterable<Employee> getAllEmployeeFromDB() {
		return empRepository.findAll();
	}
	
	public void setTaskNeeded(Boolean isTaskNeeded) {
		this.isTaskNeeded = isTaskNeeded;
	}

}
