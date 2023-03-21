package com.berke.mytvseriesapplevel2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.helpers.downloadFromUrl
import com.berke.mytvseriesapplevel2.helpers.placHolderProgressBar
import com.berke.mytvseriesapplevel2.viewModel.EpisodesViewModel
import com.berke.mytvseriesapplevel2.viewModel.OneEpisodeViewModel
import kotlinx.android.synthetic.main.fragment_episodes.*

class EpisodesFragment : Fragment() {

    private lateinit var oneEpisodesViewModel: OneEpisodeViewModel
    private var idEpisodes = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            idEpisodes = EpisodesFragmentArgs.fromBundle(it).idEpisodes
        }

        oneEpisodesViewModel = ViewModelProviders.of(this).get(OneEpisodeViewModel::class.java)
        oneEpisodesViewModel.refreshOneEpisodeData(idEpisodes)

        observeLiveDataForEpisodeData()
    }

    private fun observeLiveDataForEpisodeData(){
        oneEpisodesViewModel.oneEpisodesList.observe(viewLifecycleOwner, Observer {
            val episodeImage = it.image.original

            context?.let { episodesImageView.downloadFromUrl(episodeImage, placHolderProgressBar(it)) }

            nameTextView.text = it.name
            numareTextView.text = it.number.toString()
            imdbTextView.text = it.rating.average.toString()
            dateTextView.text = it.airdate
            timeTextView.text = it.airtime
            runtimeTextView.text = it.runtime.toString()
            summaryTextView.text = it.summary
        })


    }


}