package com.example.weatherapplication.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.Data
import com.example.weatherapplication.R
import com.example.weatherapplication.viewholder.HourlyForecastViewHolder
import com.example.weatherapplication.models.CurrentForecastModel
import com.example.weatherapplication.models.HourlyForecastModel
import com.example.weatherapplication.models.ParentModel
import com.example.weatherapplication.viewholder.BaseViewHolder
import com.example.weatherapplication.viewholder.CurrentWeatherViewHolder
import com.example.weatherapplication.viewholder.ParentRecyclerViewHolder

class WeatherAdapter(context: FragmentActivity?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val adapterDataList: MutableList<Data> = mutableListOf()

    private val context: FragmentActivity? = context

    /**Due to multiple view types each of the view type is assigned
     * a number to identify which card is to be displayed in the recycler view
     */
    companion object {
        const val CURRENT_WEATHER = 1
        const val HOURLY_WEATHER = 2
        const val WEEKLY_WEATHER = 3
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            CURRENT_WEATHER -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.current_weather_view, parent, false)
                    CurrentWeatherViewHolder(view)
            }
            HOURLY_WEATHER ->{
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.hourly_weather_details, parent, false)
                    HourlyForecastViewHolder(view)

            }
            WEEKLY_WEATHER ->{
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.parent_recycler, parent, false)
                    ParentRecyclerViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type"+ viewType)
        }
    }

    override fun getItemCount(): Int {
        if(adapterDataList!=null) {
            return adapterDataList!!.size
        }else
            return  0;

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /** An abstract class base view holder is created. A new view holder is created for every type of card
         * and the holder implements the base view holder class
         */
        val element = adapterDataList[position].data

        when (holder) {
            is HourlyForecastViewHolder ->{
                holder.bind(element as HourlyForecastModel)
                holder.setIsRecyclable(false)
            }
            is CurrentWeatherViewHolder -> {
                holder.bind(element as CurrentForecastModel)
                holder.setIsRecyclable(false)
            }
            is ParentRecyclerViewHolder->{
                holder.bind(element as ParentModel)
                holder.setIsRecyclable(false)
            }
            else -> throw IllegalArgumentException()
        }
    }

    /** Adapter uses this function to add new items in the recycler view **/
    fun setListItems(postList: Data){
        this.adapterDataList.add(postList)
        notifyItemInserted(adapterDataList.size-1)
    }

    /**To differentiate between different types , we will check the viewtype
     * stored against ech entry in the recycler view and then after identifying the
     * type , call the respective viewholder
     */
    override fun getItemViewType(position: Int): Int {
      return this.adapterDataList[position].viewType
    }
    override fun getItemId(position: Int): Long{
        return adapterDataList.get(position).viewType.toLong()
    }

}

