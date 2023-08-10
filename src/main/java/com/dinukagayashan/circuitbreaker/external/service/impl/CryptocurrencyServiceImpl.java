package com.dinukagayashan.circuitbreaker.external.service.impl;

import com.dinukagayashan.circuitbreaker.external.service.CryptocurrencyService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    @Value("${service.cryptocurrency.url}")
    public String cryptocurrencyUrl;

    @Value("${service.cryptocurrency.endpoint}")
    public String cryptocurrencyEndpoint;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    @CircuitBreaker(name = "cryptocurrencyService", fallbackMethod = "returnWait")
    public String getCryptocurrencies() throws Exception {
        try {
            return restTemplate.getForObject(cryptocurrencyUrl + cryptocurrencyEndpoint + "/get/all", String.class);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    public String returnWait(Exception exception) {
        return "Please Wait";
    }
}
