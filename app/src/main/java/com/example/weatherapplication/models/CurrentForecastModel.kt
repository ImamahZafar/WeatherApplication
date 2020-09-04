package com.example.weatherapplication.models


 data class CurrentForecastModel (
     var lat: Float,
     var lon: Float,
     var timezone: String,
     var timezone_offset: Long,
     var current:  Current,
     var city: String


 )