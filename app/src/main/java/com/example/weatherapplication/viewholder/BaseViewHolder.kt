package com.example.weatherapplication.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**Abtract class which is implemented by other view holders for their specific view model**/
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)

}
