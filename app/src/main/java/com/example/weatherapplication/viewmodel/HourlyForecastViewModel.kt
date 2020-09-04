package com.example.weatherapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.weatherapplication.models.HourlyForecastModel
import com.example.weatherapplication.retrofitservice.WeatherApi
import java.lang.Exception

private const val APP_ID= "23d0cc01856cd4cfa9c9c8f80774c4b2"
private const val LAT=31.520539999999997
private const val LON=74.41051666666667

enum class HourlyForecastStatus {  ERROR, DONE }

class HourlyForecastViewModel : ViewModel(){
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<HourlyForecastStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<HourlyForecastStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _properties = MutableLiveData<HourlyForecastModel>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<HourlyForecastModel>
        get() = _properties


    /**
     * Call getForecast() on init so we can display status immediately.
     */
    init {
        getForecast()
    }


     public fun getForecast(): MutableLiveData<HourlyForecastModel> {
         viewModelScope.launch {
      try{
            val list= WeatherApi.retrofitService.getWeather(APP_ID,LAT, LON)
          _properties.value= HourlyForecastModel(list!!.lat,list!!.lon,list!!.timezone,list!!.timezone_offset,list!!.hourly)
             _status.value=
                 HourlyForecastStatus.DONE
      }catch (e: Exception){
          _status.value =
              HourlyForecastStatus.ERROR
          _properties.value = null
      }
         }

return _properties
         }
     }

