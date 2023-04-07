package com.gintare.exam.controllers;

import com.gintare.exam.models.Forecast;
import com.gintare.exam.models.ForecastModel;
import com.gintare.exam.repositories.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastRestController {

    @Autowired
    private ForecastRepository forecastRepository;

    @PostMapping(value = "/api/forecast", consumes = "application/json")
    public void index(@RequestBody ForecastModel model) {
        Forecast entity = new Forecast();
        entity.date = model.date;
        entity.temperature = Double.toString(model.temperature);
        entity.userId = 1;
        entity.city = model.cityName;
        forecastRepository.save(entity);
    }
}
