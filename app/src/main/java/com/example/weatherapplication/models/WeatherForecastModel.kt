package com.example.weatherapplication.models

data class WeatherForecastModel (
    var lat: Float,
    var lon: Float,
    var timezone: String,
    var timezone_offset: Long,
    var current:  Current,
    var minutely: List<Minutely> = listOf<Minutely>(),
    var hourly: List<Hourly> = listOf<Hourly>(),
    var daily:   List<Daily> = listOf<Daily>()

    )