package com.example.apiretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("v1/forecast")
    Call<WeatherResponse> getCurrentWeather(
            @Query("latitude") double lat,
            @Query("longitude") double lon,
            @Query("current_weather") boolean currentWeather,
            @Query("timezone") String timezone
    );
}