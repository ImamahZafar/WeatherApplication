package com.example.weatherapplication.viewholder
import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.weatherapplication.models.CurrentForecastModel
import kotlinx.android.synthetic.main.current_weather_view.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


private const val SOURCE_URL = "http://openweathermap.org/img/wn/"

/**Viewholder to display current weather **/
class CurrentWeatherViewHolder (itemView: View) : BaseViewHolder< CurrentForecastModel>(itemView) {


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(item:CurrentForecastModel) {
        with(itemView){
            current_description.text= item.current.weather[0].description
            current_temperature.text=item.current.temp.toString()+"\u2103"
            current_location.text=item.city

            /**Making use of Glide to access weather icons **/
           var weatherIcon :String = item.current.weather[0].icon
            var source :String =SOURCE_URL+ weatherIcon+ ".png"
            Glide.with(context).load(source).into(imageView);

            val currentDateTime = LocalDateTime.now()
            current_date.text=currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
        }
    }


}