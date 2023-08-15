package com.dinukagayashan.circuitbreaker.external.service;

import java.util.concurrent.Future;

public interface CryptocurrencyService {
    Future<String> getCryptocurrencies() throws Exception;
}
