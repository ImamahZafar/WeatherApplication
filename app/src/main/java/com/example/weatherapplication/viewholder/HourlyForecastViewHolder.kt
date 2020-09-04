package com.example.weatherapplication.viewholder

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.hourly_weather_details.view.*
import com.example.weatherapplication.models.HourlyForecastModel
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId


private const val SOURCE_URL = "http://openweathermap.org/img/wn/"

class HourlyForecastViewHolder (itemView: View) : BaseViewHolder<HourlyForecastModel>(itemView) {



    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(item: HourlyForecastModel) {
        with(itemView){


           time_one_display.text=convertTime(item.hourly[0].dt).toString()
            temp_one_display.text=item.hourly[0].temp.toString()+"\u2103"
            /**Making use of Glide to access weather icons **/
            addImage(item,icon_one_display,context,0)

            time_two_display.text=convertTime(item.hourly[1].dt).toString()
            temp_two_display.text=item.hourly[1].temp.toString()+"\u2103"
            /**Making use of Glide to access weather icons **/
            addImage(item,icon_two_display,context,1)

            time_three_display.text=convertTime(item.hourly[2].dt).toString()
            temp_three_display.text=item.hourly[2].temp.toString()+"\u2103"
            /**Making use of Glide to access weather icons **/
            addImage(item,icon_three_display,context,2)

            time_four_display.text=convertTime(item.hourly[3].dt).toString()
            temp_four_display.text=item.hourly[3].temp.toString()+"\u2103"
            /**Making use of Glide to access weather icons **/
            addImage(item,icon_four_display,context,3)

            time_five_display.text=convertTime(item.hourly[4].dt).toString()
            temp_five_display.text=item.hourly[4].temp.toString()+"\u2103"
            /**Making use of Glide to access weather icons **/
            addImage(item,icon_five_display,context,4)
        }
    }

    private fun addImage( item: HourlyForecastModel,view: ImageView, context: Context,i:Int)
    {
        var weatherIcon :String = item.hourly[i].weather[0].icon
        var source :String = SOURCE_URL + weatherIcon+ ".png"
        Glide.with(context).load(source).into(view);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private  fun convertTime(time: Long): LocalTime
    {
        val dt = Instant.ofEpochSecond(time)
            .atZone(ZoneId.systemDefault()).toLocalTime()
        return dt
    }
}
