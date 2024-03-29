package com.gintare.exam.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gintare.exam.models.*;
import com.gintare.exam.repositories.ForecastRepository;
import com.gintare.exam.services.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @Autowired
    ForecastRepository forecastRepository;

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) String cityCode,
                              @RequestParam(required = false) String cityName) throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");
        var indexModel = new IndexModel();
        indexModel.currentCity = cityCode;
        indexModel.currentCityName = cityCode;//cityName;
        indexModel.cities = forecastService.getCities();
        indexModel.forecasts = forecastService.getForecasts(cityCode);
        modelAndView.addObject("IndexModel", indexModel);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        indexModel.userName = userName;
        return modelAndView;
    }

    @GetMapping("/stored-forecasts")
    public ModelAndView storedForecasts(@RequestParam(required = false) String cityCode) throws IOException {
        ModelAndView modelAndView = new ModelAndView("stored");
        var indexModel = forecastRepository.findAll();
        modelAndView.addObject("myForecasts", indexModel);
        return  modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
}