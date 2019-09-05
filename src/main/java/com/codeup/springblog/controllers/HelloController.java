package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "<h1>Hello from Mario!</h1>";
    }

    private long count = 0;
    @RequestMapping(path = "increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        count += number;
        return "<h1>Our count is now at " + count + "!<h1>";
    }

    @PostMapping("/goodbye")
    @ResponseBody
    public String goodbye() {
        return "<h4>Goodbye from Mario...</h4>";
    }
}
