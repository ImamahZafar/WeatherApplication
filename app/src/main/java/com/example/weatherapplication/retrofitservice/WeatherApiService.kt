package com.example.weatherapplication.retrofitservice

import com.example.weatherapplication.models.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**Base URL to access the Openweathermap service*/
private const val BASE_URL = "https://api.openweathermap.org"

/**Building the Moshi object that Retrofit will be using*/
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

/**Building a retrofit object using a Moshi converter with Moshi object*/
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(
    moshi
)).baseUrl(BASE_URL).build()

interface WeatherApiService {

    /** Returns a Coroutine List of WeatherForecastModel which can be fetched in coroutine scope**/
    @GET("/data/2.5/onecall")
    suspend fun getWeather (@Query("appid") APP_ID: String,
                    @Query("lat") lat: Double?,
                    @Query("lon") lon: Double?,
                    @Query("units") units: String? = "metric"): WeatherForecastModel

}

/**A public Api object that exposes the lazy-initialized Retrofit service*/
object WeatherApi {
    val retrofitService : WeatherApiService by lazy {
        //Retrofit service has been created with WeatherApiService Interface
        retrofit.create(
            WeatherApiService::class.java) }
}

