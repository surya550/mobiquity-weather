package com.mobiquity.weatherapp.ui.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.mobiquity.weatherapp.R
import com.mobiquity.weatherapp.model.ModelNextDay
import kotlinx.android.synthetic.main.list_item_next_days.view.*
import java.util.*

class NextDayAdapter(private val mContext: Context,
                     private val items: List<ModelNextDay>) : RecyclerView.Adapter<NextDayAdapter.ViewHolder>() {

    private val sharedPrefFile = "unit_preference"
    private var unit: String? = null
    private var format: String? = null

    private val sharedPreferences: SharedPreferences = mContext.getSharedPreferences(
        sharedPrefFile,
        Context.MODE_PRIVATE
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        unit = sharedPreferences.getString("unit", "")


        format = if (unit.equals("metric")) {
            "%.0f°C"
        } else
            "%.0f°f"

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_next_days, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]

        holder.tvNameDay.text = data.nameDay
        holder.tvDate.text = data.nameDate
        holder.tvMinTemp.text = String.format(Locale.getDefault(), format!!, data.tempMin)
        holder.tvMaxTemp.text = String.format(Locale.getDefault(), format!!, data.tempMax)

        when (data.descWeather) {
            "broken clouds" -> {
                holder.iconTemp.setAnimation(R.raw.broken_clouds)
            }
            "light rain" -> {
                holder.iconTemp.setAnimation(R.raw.light_rain)
            }
            "overcast clouds" -> {
                holder.iconTemp.setAnimation(R.raw.overcast_clouds)
            }
            "moderate rain" -> {
                holder.iconTemp.setAnimation(R.raw.moderate_rain)
            }
            "few clouds" -> {
                holder.iconTemp.setAnimation(R.raw.few_clouds)
            }
            "heavy intensity rain" -> {
                holder.iconTemp.setAnimation(R.raw.heavy_intentsity)
            }
            "clear sky" -> {
                holder.iconTemp.setAnimation(R.raw.clear_sky)
            }
            "scattered clouds" -> {
                holder.iconTemp.setAnimation(R.raw.scattered_clouds)
            }
            else -> {
                holder.iconTemp.setAnimation(R.raw.unknown)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListNextDays: CardView
        var tvNameDay: TextView
        var tvDate: TextView
        var tvMaxTemp: TextView
        var tvMinTemp: TextView
        var iconTemp: LottieAnimationView

        init {
            cvListNextDays = itemView.cvListNextDays
            tvNameDay = itemView.tvNameDay
            tvDate = itemView.tvDate
            tvMaxTemp = itemView.tvMaxTemp
            tvMinTemp = itemView.tvMinTemp
            iconTemp = itemView.iconTemp
        }
    }
}