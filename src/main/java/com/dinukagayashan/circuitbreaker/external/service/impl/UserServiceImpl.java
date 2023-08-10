package com.dinukagayashan.circuitbreaker.external.service.impl;

import com.dinukagayashan.circuitbreaker.external.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Value("${service.user.url}")
    public String userUrl;

    @Value("${service.user.endpoint}")
    public String userEndpoint;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    @CircuitBreaker(name = "userService", fallbackMethod = "returnWait")
    public String getUsers() throws Exception {
        try {
            return restTemplate.getForObject(userUrl + userEndpoint + "/all", String.class);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    public String returnWait(Exception exception) {
        return "Please Wait";
    }

}
