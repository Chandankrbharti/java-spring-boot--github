package com.hackerrank.github.mis;



import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name="FeignClient", url= "http://localhost:8080/")
public interface FeignClientTest {
    @RequestMapping("/greeting")
    public String greet();
}
