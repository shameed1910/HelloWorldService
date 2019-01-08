package com.example.microservices.HelloWorldService;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="FooService")
@RibbonClient(name="FooService")
public interface FooServiceProxy {
	
	@RequestMapping("/foo")
	public String fooTest();

}
