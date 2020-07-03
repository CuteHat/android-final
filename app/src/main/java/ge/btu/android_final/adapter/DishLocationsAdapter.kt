package ge.btu.android_final.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.btu.android_final.R
import ge.btu.android_final.model.Location
import kotlinx.android.synthetic.main.item_dish_location.view.*

class DishLocationsAdapter : RecyclerView.Adapter<DishLocationsAdapter.ViewHolder>() {

    private var locations: List<Location> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder (
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_dish_location,
                parent,
                false
            )
        )
    }

    fun submitItems(countriesList: List<Location>){
        locations = countriesList
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(locations.get(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(location: Location) {
            itemView.dishLocationName.text = "${location.locationNameGe}"
            itemView.dishLocation.text = "${location.locationGe}"
        }
    }

}