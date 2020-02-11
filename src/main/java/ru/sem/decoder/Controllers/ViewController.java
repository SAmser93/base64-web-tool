package ru.sem.decoder.Controllers;

//Ресты для морды

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {

    private Logger logger;

    @Autowired
    ViewController(){
        this.logger = LogManager.getLogger(ViewController.class);
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    public String goHome(){
        return "index";
    }
}

