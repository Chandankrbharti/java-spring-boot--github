package com.hackerrank.github.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {

    @ResponseBody
    @RequestMapping(method= RequestMethod.GET)
    public String welcome() {
        return "SPRING BOT REST API WORKING";
    }
}
