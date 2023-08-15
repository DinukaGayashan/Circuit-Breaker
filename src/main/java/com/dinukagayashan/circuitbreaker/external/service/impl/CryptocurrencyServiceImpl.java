package com.dinukagayashan.circuitbreaker.external.service.impl;

import com.dinukagayashan.circuitbreaker.external.service.CryptocurrencyService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Slf4j
@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    @Value("${service.cryptocurrency.url}")
    public String cryptocurrencyUrl;

    @Value("${service.cryptocurrency.endpoint}")
    public String cryptocurrencyEndpoint;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    @Async
    @Retry(name = "cryptocurrencyService")
    @CircuitBreaker(name = "cryptocurrencyService", fallbackMethod = "returnWait")
    public Future<String> getCryptocurrencies() throws Exception {
        log.info("get-cryptocurrencies-external-call");
        try {
            return new AsyncResult<>(restTemplate.getForObject(
                    cryptocurrencyUrl + cryptocurrencyEndpoint + "/get/all",
                    String.class));
        } catch (Exception exception) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }

    public Future<String> returnWait(Exception exception) {
        return new AsyncResult<>("Please Wait");
    }
}
