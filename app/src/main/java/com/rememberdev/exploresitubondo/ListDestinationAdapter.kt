package com.rememberdev.exploresitubondo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListDestinationAdapter(private val listDestination: ArrayList<Destination>) :
    RecyclerView.Adapter<ListDestinationAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvLocation: TextView = itemView.findViewById(R.id.tv_location)
        val tvOpen: TextView = itemView.findViewById(R.id.tv_open)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_destination_list, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listDestination.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val destination = listDestination[position]
//        holder.imgPhoto.setImageResource(photo)
        Glide.with(holder.itemView.context).load(destination.photo1)
            .apply(RequestOptions().override(110, 75)).into(holder.imgPhoto)

        holder.tvName.text = destination.name
        holder.tvLocation.text = destination.location
        holder.tvOpen.text = destination.open

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDestination[position])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Destination)
    }

}
