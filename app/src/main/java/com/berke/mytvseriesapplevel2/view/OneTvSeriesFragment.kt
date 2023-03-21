package com.berke.mytvseriesapplevel2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.adapter.CastAdapter
import com.berke.mytvseriesapplevel2.adapter.CrewAdapter
import com.berke.mytvseriesapplevel2.adapter.EpisoteAdapter
import com.berke.mytvseriesapplevel2.helpers.downloadFromUrl
import com.berke.mytvseriesapplevel2.helpers.placHolderProgressBar
import com.berke.mytvseriesapplevel2.models.crewModels.CrewModel
import com.berke.mytvseriesapplevel2.models.episodesModels.EpisodesModel
import com.berke.mytvseriesapplevel2.models.tvSeriesModels.Cast
import com.berke.mytvseriesapplevel2.viewModel.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_one_tv_series.*


class OneTvSeriesFragment : Fragment() {

    private lateinit var tvSeriesViewModel : TvSeriesViewModel
    private var idTvSeries = 1
    private var castAdapter = CastAdapter(arrayListOf())


    private var episodeAdapter = EpisoteAdapter(EpisodesModel())
    private lateinit var episodesViewModel: EpisodesViewModel

    private var crewAdapter = CrewAdapter(CrewModel())
    private lateinit var crewViewModel: CrewViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_tv_series, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            idTvSeries = OneTvSeriesFragmentArgs.fromBundle(it).idTvSeries
        }

        tvSeriesViewModel = ViewModelProviders.of(this).get(TvSeriesViewModel::class.java)
        tvSeriesViewModel.refreshTvSeriesDat(idTvSeries)

        castRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        castRecyclerView.adapter = castAdapter

        episodesViewModel = ViewModelProviders.of(this).get(EpisodesViewModel::class.java)
        episodesViewModel.refreshEpisodesData(idTvSeries)

        seasonsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        seasonsRecyclerView.adapter = episodeAdapter

        crewViewModel= ViewModelProviders.of(this).get(CrewViewModel::class.java)
        crewViewModel.refreshCrewData(idTvSeries)

        crewRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        crewRecyclerView.adapter = crewAdapter


        observeLiveDataForTvSeries()
        observeLiveDataForEpisode()
        observeLiveDataForCrew()


        backViewButton.setOnClickListener {
            val actionBackButton = OneTvSeriesFragmentDirections.actionOneTvSeriesFragmentToSecondeGeneralFragment()
            Navigation.findNavController(it).navigate(actionBackButton)
        }

    }

    private fun observeLiveDataForTvSeries (){
        tvSeriesViewModel.tvSeriesList.observe(viewLifecycleOwner, Observer {
            val tvSeriesImage = it.image.original
            val schedule = "${it.schedule.time} - ${it.schedule.days}"

            context?.let { tvSeriesImageView.downloadFromUrl(tvSeriesImage, placHolderProgressBar(it)) }
            tvSeriesTextView.text = it.name
            genresTextView.text = it.genres.joinToString(",")
            imdbTextView.text = it.rating.average.toString()
            summaryTextView.text = it.summary
            statusTextView.text = it.status
            runtimeTextView.text = it.runtime.toString()
            scheduleTextView.text = schedule
            premieredTextView.text = it.premiered
            endedTextView.text=it.ended
            languageTextView.text = it.language


            castAdapter.updateCastData(it._embedded.cast)
        })
    }

    private fun observeLiveDataForEpisode(){
        episodesViewModel.episodesList.observe(viewLifecycleOwner, Observer {
            episodeAdapter.refreshEpisoteData(it)
        })
    }

    private fun observeLiveDataForCrew(){
        crewViewModel.crewList.observe(viewLifecycleOwner, Observer {
            crewAdapter.refreshCrewData(it)
        })
    }

}