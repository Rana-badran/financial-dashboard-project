package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class FinancialController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/balances", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBalances() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/balances.json");
        Path path = resource.getFile().toPath();
        return new String(Files.readAllBytes(path));
    }
}
