package com.example.weatherapplication.viewholder

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.weatherapplication.models.ChildModel
import kotlinx.android.synthetic.main.child_recycler.view.*
import kotlinx.android.synthetic.main.current_weather_view.view.imageView
import java.time.Instant
import java.time.ZoneId

private const val SOURCE_URL = "http://openweathermap.org/img/wn/"

class ChildRecyclerViewHolder (itemView: View) : BaseViewHolder<ChildModel>(itemView){

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(item: ChildModel) {

            with(itemView){
            day.text= getDayOfTheWeek(item.dt)
            min_temperature.text=item.min.toString()+"\u2103"+ " / "
            max_temperature.text=item.max.toString()+"\u2103"

            /**Making use of Glide to access weather icons **/
            var weatherIcon :String = item.icon
            var source :String =SOURCE_URL+ weatherIcon+ ".png"
            Glide.with(context).load(source).into(imageView);

        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayOfTheWeek(unix: Long):String
    {
        val dt = Instant.ofEpochSecond(unix)
            .atZone(ZoneId.systemDefault())
            .dayOfWeek.toString()
        return dt
    }


}