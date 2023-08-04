package com.dinukagayashan.circuitbreaker.domain.service.impl;

import com.dinukagayashan.circuitbreaker.domain.service.WorkService;
import com.dinukagayashan.circuitbreaker.external.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    UserService userService;

    public void getUsers(){
        System.out.println(userService.getUsers());
    }
}
