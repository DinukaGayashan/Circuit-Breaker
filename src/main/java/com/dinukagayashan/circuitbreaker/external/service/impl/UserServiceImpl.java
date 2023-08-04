package com.dinukagayashan.circuitbreaker.external.service.impl;

import com.dinukagayashan.circuitbreaker.external.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Value("${service.user.url}")
    public String baseUrl;

    @Value("${service.user.endpoint}")
    public String userEndpoint;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getUsers() {
        try {
            return restTemplate.getForObject(
                    baseUrl + userEndpoint + "/all",
                    String.class);
        } catch (Exception exception) {
            return null;
        }
    }
}
