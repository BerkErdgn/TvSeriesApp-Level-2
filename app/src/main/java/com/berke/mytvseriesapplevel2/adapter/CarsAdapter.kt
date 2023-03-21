package com.berke.mytvseriesapplevel2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.helpers.downloadFromUrl
import com.berke.mytvseriesapplevel2.helpers.placHolderProgressBar
import com.berke.mytvseriesapplevel2.models.searchModels.SearchTvSeriesModels
import com.berke.mytvseriesapplevel2.view.SecondeGeneralFragmentDirections
import kotlinx.android.synthetic.main.liked_recyclerview_row.view.*

class CarsAdapter (var carsList: SearchTvSeriesModels) : RecyclerView.Adapter<CarsAdapter.CarsViewHolder>() {
    class CarsViewHolder (var view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.liked_recyclerview_row,parent,false)
        return CarsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        try {
            holder.view.tvShowImage.downloadFromUrl(carsList[position].show.image.original, placHolderProgressBar(holder.view.context))
        }catch (e:Exception){
            e.printStackTrace()
        }
        holder.view.tvShowName.text = carsList[position].show.name
        holder.view.tvshowtext2.text = carsList[position].show.genres.joinToString(",")

        holder.view.setOnClickListener {
            val action = SecondeGeneralFragmentDirections.actionSecondeGeneralFragmentToOneTvSeriesFragment(carsList[position].show.id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return carsList.size
    }

    fun updatecarsData (newCarsList : SearchTvSeriesModels){
        carsList.clear()
        carsList.addAll(newCarsList)
        notifyDataSetChanged()
    }
}