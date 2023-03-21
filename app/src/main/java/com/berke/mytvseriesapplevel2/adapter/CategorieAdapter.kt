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
import com.berke.mytvseriesapplevel2.models.searchModels.SearchTvSeriesModels
import com.berke.mytvseriesapplevel2.view.SecondeGeneralFragmentDirections
import kotlinx.android.synthetic.main.liked_recyclerview_row.view.*

class CategorieAdapter (var categoriList: SearchTvSeriesModels) : RecyclerView.Adapter<CategorieAdapter.CategorieViewHolder>() {
    class CategorieViewHolder (var view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.liked_recyclerview_row,parent,false)
        return CategorieViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategorieViewHolder, position: Int) {
        try {
            holder.view.tvShowImage.downloadFromUrl(categoriList[position].show.image.original, placHolderProgressBar(holder.view.context))
        }catch (e:Exception){
            e.printStackTrace()
        }
        holder.view.tvShowName.text = categoriList[position].show.name
        holder.view.tvshowtext2.text = categoriList[position].show.genres.joinToString(",")

        holder.view.setOnClickListener {
            val action = SecondeGeneralFragmentDirections.actionSecondeGeneralFragmentToOneTvSeriesFragment(categoriList[position].show.id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return categoriList.size
    }

    fun updateCategoriData (newcategoriList : SearchTvSeriesModels){
        categoriList.clear()
        categoriList.addAll(newcategoriList)
        notifyDataSetChanged()
    }
}