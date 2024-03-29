package com.mobiquity.weatherapp.ui.adapter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.mobiquity.weatherapp.R
import com.mobiquity.weatherapp.model.ModelMain
import kotlinx.android.synthetic.main.list_item_main.view.*
import java.util.*


class MainAdapter(context: Context, private val items: List<ModelMain>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val sharedPrefFile = "unit_preference"
    private var unit: String? = null
    private var format: String? = null

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        sharedPrefFile,
        Context.MODE_PRIVATE
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        unit = sharedPreferences.getString("unit", "")


        format = if (unit.equals("metric")) {
            "%.0f°C"
        } else
            "%.0f°f"

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        val generator = ColorGenerator.MATERIAL

        // generate random color
        val color = generator.randomColor
        holder.cvListWeather.setCardBackgroundColor(color)

        holder.tvNameDay.text = data.timeNow
        holder.tvTemp.text = String.format(Locale.getDefault(), format!!, data.currentTemp)
        holder.tvTempMin.text = String.format(Locale.getDefault(), format!!, data.tempMin)
        holder.tvTempMax.text = String.format(Locale.getDefault(), format!!, data.tempMax)

        if (data.descWeather == "broken clouds") {
            holder.iconTemp.setAnimation(R.raw.broken_clouds)
        } else if (data.descWeather == "light rain") {
            holder.iconTemp.setAnimation(R.raw.light_rain)
        } else if (data.descWeather == "overcast clouds") {
            holder.iconTemp.setAnimation(R.raw.overcast_clouds)
        } else if (data.descWeather == "moderate rain") {
            holder.iconTemp.setAnimation(R.raw.moderate_rain)
        } else if (data.descWeather == "few clouds") {
            holder.iconTemp.setAnimation(R.raw.few_clouds)
        } else if (data.descWeather == "heavy intensity rain") {
            holder.iconTemp.setAnimation(R.raw.heavy_intentsity)
        } else if (data.descWeather == "clear sky") {
            holder.iconTemp.setAnimation(R.raw.clear_sky)
        } else if (data.descWeather == "scattered clouds") {
            holder.iconTemp.setAnimation(R.raw.scattered_clouds)
        } else {
            holder.iconTemp.setAnimation(R.raw.unknown)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListWeather: CardView
        var tvNameDay: TextView
        var tvTemp: TextView
        var tvTempMin: TextView
        var tvTempMax: TextView
        var iconTemp: LottieAnimationView

        init {
            cvListWeather = itemView.cvListWeather
            tvNameDay = itemView.tvNameDay
            tvTemp = itemView.tvTemp
            tvTempMin = itemView.tvTempMin
            tvTempMax = itemView.tvTempMax
            iconTemp = itemView.iconTemp
        }
    }
}