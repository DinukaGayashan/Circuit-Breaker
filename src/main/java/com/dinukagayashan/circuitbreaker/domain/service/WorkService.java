package com.dinukagayashan.circuitbreaker.domain.service;

import java.util.concurrent.Future;

public interface WorkService {
    String getUsers() throws Exception;

    Future<String> getCryptocurrencies() throws Exception;
}
