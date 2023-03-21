package com.berke.mytvseriesapplevel2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.helpers.downloadFromUrl
import com.berke.mytvseriesapplevel2.helpers.placHolderProgressBar
import com.berke.mytvseriesapplevel2.models.crewModels.CrewModel
import com.berke.mytvseriesapplevel2.view.OneTvSeriesFragmentDirections
import kotlinx.android.synthetic.main.cast_recyclerview_raw.view.*

class CrewAdapter(val crewList : CrewModel): RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {
    class CrewViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cast_recyclerview_raw,parent,false)
        return CrewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        try {
            holder.view.castImageView.downloadFromUrl(crewList[position].person.image.medium,placHolderProgressBar(holder.view.context))
        }catch (e: Exception){
            e.printStackTrace()
        }
        holder.view.castNameTextView.text = crewList[position].person.name
        holder.view.castCharacterNameTextView.text = crewList[position].type

        holder.view.setOnClickListener {
            val action = OneTvSeriesFragmentDirections.actionOneTvSeriesFragmentToPersonFragment(crewList[position].person.id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return crewList.size
    }

    fun refreshCrewData (newCrewList : CrewModel){
        crewList.clear()
        crewList.addAll(newCrewList)
        notifyDataSetChanged()
    }
}