package com.example.apiretrofit;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("current_weather")
    public CurrentWeather currentWeather;

    public class CurrentWeather {
        public float temperature;
        @SerializedName("weathercode")
        public int weatherCode;

        @SerializedName("is_day")
        public int isDay;
    }
}