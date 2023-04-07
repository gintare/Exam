package com.gintare.exam.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gintare.exam.models.ForecastModel;
import com.gintare.exam.models.ForecastTimestamp;
import com.gintare.exam.models.Place;
import com.gintare.exam.models.Root;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class ForecastService {

    public static ArrayList<Place> getCities() throws IOException {
        var cities = new ArrayList<Place>();
        String json = loadCitiesDataJson();
        ObjectMapper om = new ObjectMapper();
        Place[] obj = om.readValue(json, Place[].class);
        for(var o : obj){
            var place = new Place();
            place.code = o.code;
            place.name = o.name;
            cities.add(place);
        }

        return cities;
    }

    public static ArrayList<ForecastModel> getForecasts(String city) throws IOException {
        if(city == null) return null;
        if(city.equals("")) return null;
        String forecatsJson = loadDataJson("https://api.meteo.lt/v1/places/"+city+"/forecasts/long-term");
        Root root = getObjectFromJson(forecatsJson);

        var forecats = new ArrayList<ForecastModel>();
        for(ForecastTimestamp forecastStamp : root.forecastTimestamps){
            forecats.add(new ForecastModel(forecastStamp.forecastTimeUtc, forecastStamp.airTemperature));
        }
        return forecats;
    }

    private static Root getObjectFromJson(String text) throws JsonProcessingException, JsonMappingException {
        ObjectMapper om = new ObjectMapper();
        Root obj = om.readValue(text, Root.class);
        return obj;
    }

    private static String loadCitiesDataJson() throws IOException {
        return loadDataJson("https://api.meteo.lt/v1/places");
    }

    private static String loadDataJson(String urlStr) throws MalformedURLException, IOException, ProtocolException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responsecode = conn.getResponseCode();

        if (responsecode != 200) {
            //logger.log(Integer.toString(responsecode));
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        }

        String text = "";
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            text += scanner.nextLine();
        }
        scanner.close();
        return text;
    }

}
