package com.dinukagayashan.circuitbreaker.domain.service.impl;

import com.dinukagayashan.circuitbreaker.domain.service.WorkService;
import com.dinukagayashan.circuitbreaker.external.service.CryptocurrencyService;
import com.dinukagayashan.circuitbreaker.external.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    UserService userService;

    @Autowired
    CryptocurrencyService cryptocurrencyService;

    public String getUsers() throws Exception {
        log.info("getUsers called");
        return userService.getUsers();
    }

    public Future<String> getCryptocurrencies() throws Exception {
        log.info("getCryptocurrencies called");
        return cryptocurrencyService.getCryptocurrencies();
    }
}
