package com.example.soccerzone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerzone.R
import com.example.soccerzone.listeners.ClickListener
import com.example.soccerzone.models.SeasonData
import com.squareup.picasso.Picasso

class SeasonsRecyclerAdapter(val context: Context, val list: List<SeasonData>, val listener: ClickListener<SeasonData>)
    : RecyclerView.Adapter<SeasonsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_seasons, parent, false)
        return SeasonsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        if (holder.adapterPosition==list.size){
            holder.view_container.visibility = View.GONE
            holder.button_loadMore.visibility = View.VISIBLE
        }
//        holder.imageView_season.setBackgroundResource(R.drawable.soccer)
        holder.textView_seasonTitle.text = list.get(position).leagueName
        holder.textView_seasonTitle.isSelected = true
        holder.textView_seasonSubTitle.text = list.get(position).start.toString() + '-' + list.get(position).end.toString()
        holder.seasons_container.setOnClickListener {
            listener.onClicked(list.get(holder.adapterPosition))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
class SeasonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var seasons_container: CardView = itemView.findViewById(R.id.seasons_container)
    var imageView_season: ImageView = itemView.findViewById(R.id.imageView_season)
    var textView_seasonTitle: TextView = itemView.findViewById(R.id.textView_seasonTitle)
    var textView_seasonSubTitle: TextView = itemView.findViewById(R.id.textView_seasonSubTitle)
    var view_container: LinearLayout = itemView.findViewById(R.id.view_container)
    var button_loadMore: Button = itemView.findViewById(R.id.button_loadMore)
}