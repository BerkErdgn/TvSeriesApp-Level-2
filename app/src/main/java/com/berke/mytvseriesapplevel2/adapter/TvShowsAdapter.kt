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

class TvShowsAdapter (var tvShowsList: SearchTvSeriesModels) : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {
    class TvShowsViewHolder (var view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.liked_recyclerview_row,parent,false)
        return TvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        try {
            holder.view.tvShowImage.downloadFromUrl(tvShowsList[position].show.image.original, placHolderProgressBar(holder.view.context))
        }catch (e:Exception){
            e.printStackTrace()
        }
        holder.view.tvShowName.text = tvShowsList[position].show.name
        holder.view.tvshowtext2.text = tvShowsList[position].show.genres.joinToString(",")

        holder.view.setOnClickListener {
            val action = SecondeGeneralFragmentDirections.actionSecondeGeneralFragmentToOneTvSeriesFragment(tvShowsList[position].show.id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return tvShowsList.size
    }

    fun updateTvShowsData (newtvShowsList : SearchTvSeriesModels){
        tvShowsList.clear()
        tvShowsList.addAll(newtvShowsList)
        notifyDataSetChanged()
    }
}