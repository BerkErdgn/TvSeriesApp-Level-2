package com.berke.mytvseriesapplevel2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.helpers.downloadFromUrl
import com.berke.mytvseriesapplevel2.helpers.placHolderProgressBar
import com.berke.mytvseriesapplevel2.models.allTvModels.AllTvSeriesModels
import com.berke.mytvseriesapplevel2.view.AllTvSeriesFragmentDirections
import com.berke.mytvseriesapplevel2.view.SecondeGeneralFragmentDirections
import kotlinx.android.synthetic.main.liked_recyclerview_row.view.*

class AllTvSeriesAdapeter ( val allTvSeriesList: AllTvSeriesModels) : RecyclerView.Adapter<AllTvSeriesAdapeter.AllTvSeriesViewHolder>() {
    class AllTvSeriesViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTvSeriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.liked_recyclerview_row,parent,false)
        return AllTvSeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllTvSeriesViewHolder, position: Int) {
        holder.view.tvShowImage.downloadFromUrl(allTvSeriesList[position].image.original, placHolderProgressBar(holder.view.context))
        holder.view.tvShowName.text = allTvSeriesList[position].name
        holder.view.tvshowtext2.text = allTvSeriesList[position].genres.joinToString(",")

        holder.view.setOnClickListener {
            val action = SecondeGeneralFragmentDirections.actionSecondeGeneralFragmentToOneTvSeriesFragment(allTvSeriesList[position].id)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return allTvSeriesList.size
    }

    fun updateAllTvSeriesData (newAllTvSeriesList : AllTvSeriesModels ){
        allTvSeriesList.clear()
        allTvSeriesList.addAll(newAllTvSeriesList)
        notifyDataSetChanged()
    }
}