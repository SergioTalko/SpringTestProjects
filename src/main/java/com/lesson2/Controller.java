package com.lesson2;

import org.springframework.beans.factory.annotation.Autowired;





@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Route route;
    @Autowired
    private Service service;
    @Autowired
    private Step step;




    public void callByBean(){
        route.getId();
        route.getSteps();

        service.getId();
        service.getName();
        service.getParamsToCall();

        step.getId();
        step.getParamsServiceFrom();
        step.getParamsServiceTo();
        step.getServiceFrom();
        step.getServiceTo();
    }
}
