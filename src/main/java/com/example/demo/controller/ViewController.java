package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showMsg() {
        System.out.println("Hello server");
        return "Hello server";
    }
}
