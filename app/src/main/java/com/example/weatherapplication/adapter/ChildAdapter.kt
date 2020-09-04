package com.example.weatherapplication.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.models.ChildModel
import com.example.weatherapplication.viewholder.ChildRecyclerViewHolder

/**ChildAdapter the nested recycler view
 *
 */

class ChildAdapter(private val children: List<ChildModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var adapterDataList: List<ChildModel> = children

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildRecyclerViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_recycler, parent, false)
        return ChildRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (children != null) {
            return children.size
        } else return 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val element = adapterDataList!![position]

        when (holder) {
            is ChildRecyclerViewHolder -> holder.bind(element as ChildModel)
            else -> throw IllegalArgumentException()
        }
    }


}