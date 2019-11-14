package com.renanlukas.feature.simulator.presentation.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.renanlukas.feature.core.ui.DescriptionValueView
import com.renanlukas.feature.simulator.R

class OverviewSimulationAdapter : RecyclerView.Adapter<OverviewSimulationAdapter.ViewHolder>() {

    private val entities: MutableList<DescriptionValueView.Entity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_description_value,
                parent
            )
        )

    override fun getItemCount(): Int = entities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entities[position])
    }

    fun load(newEntities: List<DescriptionValueView.Entity>) {
        entities.clear()
        entities.addAll(newEntities)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(entity: DescriptionValueView.Entity) {
            (view as DescriptionValueView).bind(entity)
        }
    }
}