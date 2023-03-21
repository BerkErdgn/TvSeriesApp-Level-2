package com.berke.mytvseriesapplevel2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.helpers.downloadFromUrl
import com.berke.mytvseriesapplevel2.helpers.placHolderProgressBar
import com.berke.mytvseriesapplevel2.models.tvSeriesModels.Cast
import com.berke.mytvseriesapplevel2.view.OneTvSeriesFragmentDirections
import kotlinx.android.synthetic.main.cast_recyclerview_raw.view.*

class CastAdapter(val castList : ArrayList<Cast>): RecyclerView.Adapter<CastAdapter.CastHolder>() {
    class CastHolder(var view: View):RecyclerView.ViewHolder(view)  {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cast_recyclerview_raw,parent,false)
        return CastHolder(view)
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        try {
            holder.view.castImageView.downloadFromUrl(castList[position].character.image.medium, placHolderProgressBar(holder.view.context))
        }catch (e: Exception){
            e.printStackTrace()
        }
        holder.view.castNameTextView.text = castList[position].person.name
        holder.view.castCharacterNameTextView.text = castList[position].character.name

        holder.view.setOnClickListener {
            val action = OneTvSeriesFragmentDirections.actionOneTvSeriesFragmentToPersonFragment(castList[position].person.id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
      return castList.size
    }

    fun updateCastData(newCastList : ArrayList<Cast>){
        castList.clear()
        castList.addAll(newCastList)
        notifyDataSetChanged()

    }

}