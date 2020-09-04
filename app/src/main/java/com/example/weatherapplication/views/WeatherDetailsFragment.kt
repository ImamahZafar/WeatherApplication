package com.example.weatherapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.Data
import com.example.weatherapplication.R
import com.example.weatherapplication.adapter.WeatherAdapter
import com.example.weatherapplication.databinding.FragmentWeatherDetailsBinding
import com.example.weatherapplication.models.*
import com.example.weatherapplication.viewmodel.*

@Suppress("INCOMPATIBLE_ENUM_COMPARISON")

class WeatherDetailsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var userPostAdapter: WeatherAdapter

    /**Lazily initialize our CurrentWeatherModel*/
    private val viewModel: CurrentWeatherViewModel by lazy {
        ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
    }
    /**Lazily initialize our HourlyWeatherModel*/
    private val hourlyForecastViewModel: HourlyForecastViewModel by lazy {
        ViewModelProvider(this).get(HourlyForecastViewModel::class.java)
    }
    /**Lazily initialize our WeeklyWeatherModel*/
    private val weeklyForecastModel: WeeklyForecastViewModel by lazy {
        ViewModelProvider(this).get(WeeklyForecastViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
           val binding = FragmentWeatherDetailsBinding.inflate(inflater)

        /**populate the adapter**/
       populateAdapter()

        /**Allows Data Binding to Observe LiveData with the lifecycle of this Fragment**/
        binding.setLifecycleOwner(this)

        /**Initilize the recyclerView**/
        initViews(binding.root)

        /**Adding success/error states in the app**/
        showStatus()

        return binding.root

    }


    private fun initViews(view: View){
        recyclerView = view.findViewById(R.id.recycler_view)as RecyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            userPostAdapter = WeatherAdapter(activity)
            adapter =userPostAdapter
        }
    }

    private fun populateAdapter() {


        viewModel.properties.observe(viewLifecycleOwner, Observer<CurrentForecastModel>() {
            it?.let {
                userPostAdapter.setListItems(Data(WeatherAdapter.CURRENT_WEATHER, it))
            }
        })

        hourlyForecastViewModel.properties.observe(
            viewLifecycleOwner,
            Observer<HourlyForecastModel>() {
                it?.let {
                    userPostAdapter.setListItems(Data(WeatherAdapter.HOURLY_WEATHER, it))
                }
            })

        weeklyForecastModel.properties.observe(viewLifecycleOwner, Observer<ParentModel>() {
            it?.let {
                userPostAdapter.setListItems(Data(WeatherAdapter.WEEKLY_WEATHER, it))
            }
        })

    }

    private fun showStatus()
    {
        viewModel.status.observe(viewLifecycleOwner, Observer<CurrentForecastStatus>(){
            it?.let{
                    if(it!= CurrentForecastStatus.DONE)
                        Toast.makeText(activity,"Error Updating Weather",Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(activity," Weather Successfully Updated",Toast.LENGTH_LONG).show()

            }
        })
        hourlyForecastViewModel.status.observe(viewLifecycleOwner, Observer<HourlyForecastStatus>(){
            it?.let{
                if(it!= HourlyForecastStatus.DONE)
                    Toast.makeText(activity,"Error Updating Hourly Weather Forecast",Toast.LENGTH_LONG).show()
                else
                Toast.makeText(activity," Weather Successfully Updated",Toast.LENGTH_LONG).show()
            }
        })
        weeklyForecastModel.status.observe(viewLifecycleOwner, Observer<WeeklyForecastStatus>(){
            it?.let{
                if(it!= WeeklyForecastStatus.DONE)
                    Toast.makeText(activity,"Error Updating Daily Weather Forecast",Toast.LENGTH_LONG).show()
                else
                Toast.makeText(activity," Weather Successfully Updated",Toast.LENGTH_LONG).show()

            }
        })

//        if(check==false)
//        Toast.makeText(activity," Weather Successfully Updated",Toast.LENGTH_LONG).show()


    }




}