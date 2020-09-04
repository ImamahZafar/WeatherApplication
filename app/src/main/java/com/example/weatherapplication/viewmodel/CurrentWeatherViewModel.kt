package com.example.weatherapplication.viewmodel

import android.app.Application
import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.*
import com.example.weatherapplication.models.CurrentForecastModel
import com.example.weatherapplication.retrofitservice.WeatherApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

private const val APP_ID= "23d0cc01856cd4cfa9c9c8f80774c4b2"
private const val LAT=31.520539999999997
private const val LON=74.41051666666667

enum class CurrentForecastStatus {  ERROR, DONE }

class CurrentWeatherViewModel(application: Application) : AndroidViewModel(application){

    /**The internal MutableLiveData that stores the status of the request**/
    private val _status = MutableLiveData<CurrentForecastStatus>()

    /**The external immutable LiveData for the request status**/
    val status: LiveData<CurrentForecastStatus>
        get() = _status

    /**Internally, we use a MutableLiveData, because we will be updating the weather
     with new values**/
    private val _properties = MutableLiveData<CurrentForecastModel>()

    /** The external LiveData interface to the property is immutable, so only this class can modify**/
    val properties: LiveData<CurrentForecastModel>
        get() = _properties


    /**
     * Call getForecast() on init so we can display status immediately.
     */
    init {
        getForecast()
    }


    /**
     * Calls the Retrofit Api Service to get the current weather data and then initlize [_properties]
     * with it. Also updates the [_status]
     *  Use of coroutine to access the retrofit service
     */
    fun getForecast(): MutableLiveData<CurrentForecastModel> {

        viewModelScope.launch {
            try{
                val list=
                    WeatherApi.retrofitService.getWeather(APP_ID,LAT, LON)
                _properties.value= CurrentForecastModel(list!!.lat, list!!.lon, list!!.timezone, list!!.timezone_offset,list!!.current,getCity())
                _status.value=CurrentForecastStatus.DONE
            }catch (e: Exception){
                _status.value = CurrentForecastStatus.ERROR
                _properties.value = null
            }
        }

        return _properties
    }

   private  fun getCity():String
    {
        val geocoder = Geocoder(getApplication(), Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(LAT, LON, 1)
        val cityName: String = addresses[0].locality
        return cityName
    }
}

