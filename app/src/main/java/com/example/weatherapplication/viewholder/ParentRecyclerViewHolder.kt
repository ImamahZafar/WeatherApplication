package com.example.weatherapplication.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.models.ParentModel
import com.example.weatherapplication.adapter.ChildAdapter
import kotlinx.android.synthetic.main.parent_recycler.view.*

class ParentRecyclerViewHolder  (itemView: View) : BaseViewHolder<ParentModel>(itemView) {

    override fun bind(item: ParentModel) {
        with(itemView) {

            /**Creating Child Adapter.
             * Setting the layout as Horizontal and passing data for the
             * Child Adapter to show
             */
            val recyclerView : RecyclerView = itemView.rv_child
            recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            recyclerView.adapter = ChildAdapter(item.children)
        }
    }

}