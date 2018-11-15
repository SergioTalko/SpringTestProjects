package com.lesson2;

import org.springframework.beans.factory.annotation.Autowired;


public class Controller {

    @Autowired
    private Route route;

    @Autowired
    private Service service;
    @Autowired
    private Step step;




    public void callByBean(){

    }
}
