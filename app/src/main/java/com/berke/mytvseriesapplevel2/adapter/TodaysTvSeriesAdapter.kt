package com.berke.mytvseriesapplevel2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.helpers.downloadFromUrl
import com.berke.mytvseriesapplevel2.helpers.placHolderProgressBar
import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModels
import com.berke.mytvseriesapplevel2.view.SecondeGeneralFragmentDirections
import kotlinx.android.synthetic.main.today_recyclerview_row.view.*

class TodaysTvSeriesAdapter (val todayTvSeriesList: TodaysTvSeriesModels, val  viewPager: ViewPager2): RecyclerView.Adapter<TodaysTvSeriesAdapter.TodaysTvSeriesViewHolder>() {
    class TodaysTvSeriesViewHolder (var view : View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaysTvSeriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.today_recyclerview_row,parent,false)
        return TodaysTvSeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodaysTvSeriesViewHolder, position: Int) {

        try {
            holder.view.todaySeriesImage.downloadFromUrl(todayTvSeriesList[position]._embedded.show.image.medium, placHolderProgressBar(holder.view.context))
            if (position == todayTvSeriesList.size -2){
                viewPager.post(run) }

        }catch (e: Exception){
            e.printStackTrace()
        }
        holder.view.setOnClickListener {
            val action = SecondeGeneralFragmentDirections.actionSecondeGeneralFragmentToOneTvSeriesFragment(todayTvSeriesList[position]._embedded.show.id)
            Navigation.findNavController(it).navigate(action)
        }

     }


    override fun getItemCount(): Int {
        return todayTvSeriesList.size
    }


    fun updateTodaysTvSeriesList(newTodaysTvSeriesList: TodaysTvSeriesModels){
        todayTvSeriesList.clear()
        todayTvSeriesList.addAll(newTodaysTvSeriesList)
        notifyDataSetChanged()
    }



   private val run = object : Runnable{
        override fun run() {
           todayTvSeriesList.addAll(todayTvSeriesList)
            notifyDataSetChanged()
        }
    }

}