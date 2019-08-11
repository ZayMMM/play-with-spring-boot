package com.demo.springmvc3.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class WhitelabelErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(Model model, HttpServletRequest request){

        Object obj = request.getAttribute( RequestDispatcher.ERROR_STATUS_CODE );

        if(obj != null){
            int status = Integer.valueOf( obj.toString() );

            if(status == 404 ){
                model.addAttribute( "msg", "Not Found!");
                model.addAttribute( "status", status );
            }else if(status == 403){
                model.addAttribute( "msg", "you are Forbidden" );
                model.addAttribute( "status", status );
            }
        }

        return PATH;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
