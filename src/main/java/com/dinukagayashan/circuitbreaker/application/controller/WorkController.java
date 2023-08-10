package com.dinukagayashan.circuitbreaker.application.controller;

import com.dinukagayashan.circuitbreaker.domain.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("work")
public class WorkController {

    @Autowired
    WorkService workService;

    @GetMapping
    public ResponseEntity<String> getWork() throws Exception {
        return ResponseEntity.ok(workService.getUsers() + "\n" + workService.getCryptocurrencies());
    }

    @GetMapping("/users")
    public ResponseEntity<String> getUsers() throws Exception {
        return ResponseEntity.ok(workService.getUsers());
    }

    @GetMapping("/cryptocurrencies")
    public ResponseEntity<String> getCryptocurrencies() throws Exception {
        return ResponseEntity.ok(workService.getCryptocurrencies());
    }
}
