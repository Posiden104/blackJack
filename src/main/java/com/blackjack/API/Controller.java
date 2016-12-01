package com.blackjack.API;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by posid on 11/20/2016
 */
@RestController
public class Controller {

    /* Index endpoint */
    @RequestMapping("/")
    public String index(){
        return "BlackJackServer API index location.";
    }

    /* Test endpoint */
    @RequestMapping("/testing")
    public String testing(){
        return "This is a test endpoint";
    }

    /* Request update */
    @RequestMapping("/update")
    public String update(){
        return "no update yet";
    }

}
