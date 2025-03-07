package com.example.script.controller;


import com.example.script.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/start")
public class StartController {

    @Autowired
    private StartService startService;

    @GetMapping(path = "/startApplication")
    public void startApplication() throws IOException {
       startService.createConstructionIndicators();
       startService.createDistrictsAndSubdistricts();
    }
}
