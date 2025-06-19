package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.api.Response.WeatherResponse;
import net.engineeringdigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_"+city, WeatherResponse.class);
        if(weatherResponse != null) {
            return weatherResponse;
        }else{
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.API_KEY, city).replace(Placeholders.CITY,apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null,WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null) {
                redisService.set("weather_of_"+city,body,300l);
            }
            return body;
        }


//        How we can send Post request
//        START
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key","value");
//        User user = User.builder().userName("Asif").password("asif").build();
//        HttpEntity httpEntity = new HttpEntity(user,httpHeaders);
//        ResponseEntity<User> response = restTemplate.exchange(finalAPI,HttpMethod.POST,httpEntity,User.class);
//        END
    }
}
