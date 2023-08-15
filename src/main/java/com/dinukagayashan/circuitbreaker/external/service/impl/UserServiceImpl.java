package com.dinukagayashan.circuitbreaker.external.service.impl;

import com.dinukagayashan.circuitbreaker.external.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${service.user.url}")
    public String userUrl;

    @Value("${service.user.endpoint}")
    public String userEndpoint;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    @Retry(name = "userService")
    @RateLimiter(name = "userService")
    @CircuitBreaker(name = "userService", fallbackMethod = "returnWait")
    public String getUsers() throws Exception {
        log.info("get-users-external-call");
        try {
            return restTemplate.getForObject(
                    userUrl + userEndpoint + "/all",
                    String.class);
        } catch (Exception exception) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
//            throw new IOException();
        }
    }

    public String returnWait(Exception exception) {
        return "Please Wait";
    }

}
