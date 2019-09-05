package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class MathController {

    @GetMapping("/add/3/and/4")
    @ResponseBody
    public String addForSeven() {
        return "7";
    }

    @GetMapping("/subtract/3/from/10")
    @ResponseBody
    public String subtractForSeven() {
        return "7";
    }

    @GetMapping("/multiply/4/and/5")
    @ResponseBody
    public String multiplyForTwenty() {
        return "20";
    }

    @GetMapping("/divide/6/by/3")
    @ResponseBody
    public String DivideForTwo() {
        return "2";
    }
}


