package org.innoskrit.hello_world.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
class HelloWorldController {
    @GetMapping("/")
    public String helloWold() {
        return "Hello World!";
    }
}