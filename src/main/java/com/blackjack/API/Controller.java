package com.blackjack.API;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by posid on 11/20/2016.
 */
@RestController
public class Controller {

    @RequestMapping("/")
    public String index(){
        return "BlackJack API index location.";
    }

    @RequestMapping("/testing")
    public String testing(){
        return "This is a test endpoint";
    }

    @RequestMapping("/")
}
