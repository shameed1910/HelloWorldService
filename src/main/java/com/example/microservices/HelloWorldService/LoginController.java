package com.example.microservices.HelloWorldService;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class LoginController {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FooServiceProxy proxy;

	@RequestMapping("/login")
	@ResponseBody
	@HystrixCommand(fallbackMethod = "login_Fallback")
	public String login() {

		String response = "Hello World";
		logger.info("{}", response);
/*		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resp = restTemplate.getForEntity("http://localhost:9090/foo", String.class);
		logger.info("Resp" + resp.getBody());
*/		
		String Resp1 =	proxy.fooTest();
		logger.info("Resp1" + Resp1);

		return Resp1;

	}
	
	public String login_Fallback(){
        System.out.println("Foo Service is down!!! fallback route enabled...");
        
        return "CIRCUIT BREAKER ENABLED!!! No Response From Foo Service at this moment. " +
                    " Service will be back shortly - " + new Date();

	}

}
