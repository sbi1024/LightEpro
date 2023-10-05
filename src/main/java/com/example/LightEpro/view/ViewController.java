package com.example.LightEpro.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ViewController {
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String slash() throws Exception {
        return "login";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() throws Exception {
        return "login";
    }

    @RequestMapping(value = "/home", method = {RequestMethod.GET})
    public String home() throws Exception {
        return "home";
    }
}
