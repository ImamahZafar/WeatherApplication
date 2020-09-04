package com.example.weatherapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.models.ChildModel
import kotlinx.coroutines.launch
import com.example.weatherapplication.models.ParentModel
import com.example.weatherapplication.retrofitservice.WeatherApi
import java.lang.Exception

private const val APP_ID= "23d0cc01856cd4cfa9c9c8f80774c4b2"
private const val LAT=31.520539999999997
private const val LON=74.41051666666667

enum class WeeklyForecastStatus {  ERROR, DONE }

class WeeklyForecastViewModel : ViewModel(){
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<WeeklyForecastStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<WeeklyForecastStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _properties = MutableLiveData<ParentModel>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<ParentModel>
        get() = _properties


    /**
     * Call getForecast() on init so we can display status immediately.
     */
    init {
        getForecast()
    }


    public fun getForecast(): MutableLiveData<ParentModel> {
        viewModelScope.launch {
            try{
                val list=
                    WeatherApi.retrofitService.getWeather(APP_ID,LAT, LON)
                var forecast = mutableListOf<ChildModel>()
                val weekweather=list!!.daily
                for(weekWeather in weekweather)
                {
                    val newString=ChildModel(weekWeather.dt,weekWeather.temp.min,weekWeather.temp.max,weekWeather.weather[0].icon)
                    forecast.add(newString)
                }

                _properties.value=ParentModel(forecast)
                _status.value=
                    WeeklyForecastStatus.DONE
                Log.i("p", "val"+_properties.value)

            }catch (e: Exception){
                _status.value =
                    WeeklyForecastStatus.ERROR
                _properties.value = null
            }
        }

        return _properties
    }
}

