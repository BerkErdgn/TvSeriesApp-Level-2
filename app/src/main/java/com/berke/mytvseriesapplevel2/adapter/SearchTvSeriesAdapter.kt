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
import kotlinx.android.synthetic.main.search_recyclerview_row.view.*

class SearchTvSeriesAdapter(val searchTvSeriesList: SearchTvSeriesModels): RecyclerView.Adapter<SearchTvSeriesAdapter.SearchTvSeriesViewHolder>() {
    class SearchTvSeriesViewHolder(var view : View) :RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTvSeriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.search_recyclerview_row,parent,false)
        return SearchTvSeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchTvSeriesViewHolder, position: Int) {
        try {
            holder.view.searchTvSeriesImageView.downloadFromUrl(searchTvSeriesList[position].show.image.medium, placHolderProgressBar(holder.view.context))
        }catch (e : Exception){
            e.printStackTrace()
        }
        holder.view.searchTvSeiresName.text = searchTvSeriesList[position].show.name
        holder.view.searchTvSeriesRating.text = searchTvSeriesList[position].show.rating.average.toString()
        holder.view.searchTvSeriesLanguage.text = searchTvSeriesList[position].show.language
        holder.view.searchTvSeriesGenres.text = searchTvSeriesList[position].show.genres.joinToString(",")
        holder.view.searchTvSeriesDay.text = searchTvSeriesList[position].show.schedule.days.toString()
        holder.view.searchTvSeriesTime.text = searchTvSeriesList[position].show.schedule.time
        holder.view.searchTvSeriesPremiered.text = searchTvSeriesList[position].show.premiered
        holder.view.searchTvSeriesEnded.text = searchTvSeriesList[position].show.ended

        holder.view.setOnClickListener {
            val action = SecondeGeneralFragmentDirections.actionSecondeGeneralFragmentToOneTvSeriesFragment(searchTvSeriesList[position].show.id)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return searchTvSeriesList.size
    }

    fun updateSearchTvSeriesList(newSearchTvSeriesList : SearchTvSeriesModels){
        searchTvSeriesList.clear()
        searchTvSeriesList.addAll(newSearchTvSeriesList)
        notifyDataSetChanged()
    }
}