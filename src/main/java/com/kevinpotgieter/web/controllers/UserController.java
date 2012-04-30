package com.kevinpotgieter.web.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by IntelliJ IDEA.
 * User: kevinpotgieter
 * Date: 19/04/2012
 * Time: 08:20
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class UserController {

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String getUserInputForm(Model model){

        return "index";

    }






}
