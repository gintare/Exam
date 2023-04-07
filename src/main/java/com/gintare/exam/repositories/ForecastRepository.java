package com.gintare.exam.repositories;

import com.gintare.exam.models.Forecast;
import org.springframework.data.repository.CrudRepository;

public interface ForecastRepository extends CrudRepository< Forecast, Long> {
}