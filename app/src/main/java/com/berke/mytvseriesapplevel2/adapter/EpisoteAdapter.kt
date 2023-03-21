package com.berke.mytvseriesapplevel2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.helpers.downloadFromUrl
import com.berke.mytvseriesapplevel2.helpers.placHolderProgressBar
import com.berke.mytvseriesapplevel2.models.episodesModels.EpisodesModel
import com.berke.mytvseriesapplevel2.view.OneTvSeriesFragmentDirections
import kotlinx.android.synthetic.main.seasons_recycler_row.view.*

class EpisoteAdapter(val episodeList: EpisodesModel) : RecyclerView.Adapter<EpisoteAdapter.EpisoteViewHolder>() {
    class EpisoteViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.seasons_recycler_row,parent,false)
        return EpisoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisoteViewHolder, position: Int) {
        try {
            holder.view.seasonsImageView.downloadFromUrl(episodeList[position].image.original, placHolderProgressBar(holder.view.context))
        }catch (e : Exception){
           e.printStackTrace()
        }
        holder.view.seasonsNameTextView.text = episodeList[position].name
        holder.view.seasonsNoTextView.text = episodeList[position].number.toString()

        holder.view.setOnClickListener {
            val action = OneTvSeriesFragmentDirections.actionOneTvSeriesFragmentToEpisodesFragment(episodeList[position].id)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
       return episodeList.size
    }

    fun refreshEpisoteData (newEpisodeList : EpisodesModel){
        episodeList.clear()
        episodeList.addAll(newEpisodeList)
        notifyDataSetChanged()
    }
}