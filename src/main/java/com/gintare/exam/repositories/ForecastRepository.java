package com.gintare.exam.repositories;

import com.gintare.exam.models.Forecast;
import com.gintare.exam.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ForecastRepository extends CrudRepository< Forecast, Long> {

}