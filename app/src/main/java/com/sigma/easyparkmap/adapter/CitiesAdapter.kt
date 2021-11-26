package com.sigma.easyparkmap.adapter

import android.content.Context
import android.content.Intent
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sigma.easyparkmap.R
import com.sigma.easyparkmap.activity.MapsActivity
import com.sigma.easyparkmap.helper.LocationHelper
import com.sigma.easyparkmap.module.Cities

class CitiesAdapter(
    private val dataSet: ArrayList<Cities>,
    private val location: Location,
    private val context: Context
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityNameText: TextView = view.findViewById(R.id.city_name_text)
        val distanceText: TextView = view.findViewById(R.id.distance_text)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.list_cities, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.cityNameText.text = dataSet[position].name
        viewHolder.distanceText.text = "${
            LocationHelper().distanceInKm(
                location.latitude, location.longitude,
                dataSet[position].lat!!, dataSet[position].lon!!
            ).toString()
        } KM"

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            intent.putExtra("city", dataSet[position].points)
            context.startActivity(intent)

        }
    }

    override fun getItemCount() = dataSet.size


}
