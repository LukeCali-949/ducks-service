package iu.edu.lukemeng.ducksservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @GetMapping("/")
    public String greetings(){
        return "Welcome to the ducks service";
    }


}
