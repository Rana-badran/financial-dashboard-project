package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;

 import java.nio.file.Files;
 import java.nio.file.Path;

 import java.util.stream.Collectors;


@RestController
public class FinancialController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/balances", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBalances() throws IOException {
        
        InputStream resource = new ClassPathResource("balances.json").getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
        String balances = reader.lines().collect(Collectors.joining("\n"));
        return new String(balances);
    }
}
