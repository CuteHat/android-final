package ge.btu.android_final.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ge.btu.android_final.DishItemOnClickListener
import ge.btu.android_final.R
import ge.btu.android_final.model.Data
import kotlinx.android.synthetic.main.layout_dish_list_item.view.*

class DishesRecyclerAdapter(clickListener: DishItemOnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var recyclerAdapterItems: ArrayList<Data> = ArrayList()
    private var onClickListener: DishItemOnClickListener = clickListener

    fun setItems(dishesList: ArrayList<Data>) {
        recyclerAdapterItems = dishesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DishViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_dish_list_item,
                parent,
                false
            ),
            onClickListener
        )
    }

    override fun getItemCount(): Int {
        return recyclerAdapterItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DishViewHolder -> {
                holder.bind(recyclerAdapterItems[position])
            }
        }
    }

    class DishViewHolder
    constructor(
        itemView: View,
        onClickListener: DishItemOnClickListener
    ) : RecyclerView.ViewHolder(itemView) {
        private var onClickListener: DishItemOnClickListener = onClickListener

        fun bind(dish: Data) {
            itemView.text_dish.text = dish.dishnameGe
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(dish.dishImageUrl)
                .into(itemView.img_dish)

            itemView.setOnClickListener { onClickListener.clickEventHandler(dish) }
        }
    }
}