package com.berke.mytvseriesapplevel2.view

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.adapter.*
import com.berke.mytvseriesapplevel2.models.allTvModels.AllTvSeriesModels
import com.berke.mytvseriesapplevel2.models.searchModels.SearchTvSeriesModels
import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModels
import com.berke.mytvseriesapplevel2.viewModel.*
import kotlinx.android.synthetic.main.fragment_all_tv_series.*
import java.util.Date
import kotlin.math.abs


class AllTvSeriesFragment : Fragment() {

private lateinit var todayTvSeriesViewModel: TodayTvSeriesViewModel
private lateinit var todaysTvSeriesAdapter : TodaysTvSeriesAdapter
private lateinit var sliderItemList : TodaysTvSeriesModels
private lateinit var sliderHandler: Handler
private lateinit var sliderRun: Runnable

private  var allTvSeriesAdapeter = AllTvSeriesAdapeter (AllTvSeriesModels())
private lateinit var allTvSeriesViewModel: AllTvSeriesViewModel


private lateinit var carsViewModel : CarsViewModel
private lateinit var searchViewModel: SearchViewModel
private var categorieAdapter = CategorieAdapter (SearchTvSeriesModels())
private var carsAdapter = CarsAdapter(SearchTvSeriesModels())
private lateinit var tvShowSeriesViewModel : TvShowsTvSeriesViewModel
private var tvShowsAdapter = TvShowsAdapter(SearchTvSeriesModels())




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_tv_series, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//----------------------------------1------Slider-----ViewPager2-----WithAPİ------------------------
        val sdf =SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val currentime = sdf.format(date)
        println(currentime)

        todayTvSeriesViewModel = ViewModelProviders.of(this).get(TodayTvSeriesViewModel::class.java)
        todayTvSeriesViewModel.refreshTodayData(currentime)

        sliderItems()
        itemSliderView()
//-----------------------------------1-----Slider-----ViewPager2-----WithAPİ------------------------

        allTvSeriesViewModel = ViewModelProviders.of(this).get(AllTvSeriesViewModel::class.java)
        allTvSeriesViewModel.refreshAllData()

        popularTvSeriesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        popularTvSeriesRecyclerView.adapter= allTvSeriesAdapeter

        AllTvSeriesObserLiveData()

//for CarTvSeriesList
        carsViewModel = ViewModelProviders.of(this).get(CarsViewModel::class.java)
        carsViewModel.refreshCarsData("car")

        carsTvSeriesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        carsTvSeriesRecyclerView.adapter = carsAdapter

        ObserveLiveDataForCars()

//For HealthTvSeries
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchViewModel.refreshSearchData("health")

        healthTvSeriesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        healthTvSeriesRecyclerView.adapter = categorieAdapter

        ObserveLiveDataForCategories()


//For Tv Shows
        tvShowSeriesViewModel = ViewModelProviders.of(this).get(TvShowsTvSeriesViewModel::class.java)
        tvShowSeriesViewModel.refreshTvShowsData("tv shows")

        tvShowsTvSeriesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        tvShowsTvSeriesRecyclerView.adapter = tvShowsAdapter

        ObserveLiveDataForTvShows()
    }

    private fun AllTvSeriesObserLiveData (){
        allTvSeriesViewModel.allTvSeriesList.observe(viewLifecycleOwner, Observer {
            allTvSeriesAdapeter.updateAllTvSeriesData(it)
        })
    }

//-----------------------------------2-----Slider-----ViewPager2-----WithAPİ------------------------
    private fun sliderItems() {
        sliderItemList = TodaysTvSeriesModels()
        todaysTvSeriesAdapter = TodaysTvSeriesAdapter(sliderItemList,todaysTvSeriesViews)
        todaysTvSeriesViews.adapter =todaysTvSeriesAdapter
        todaysTvSeriesViews.clipToPadding = false
        todaysTvSeriesViews.clipChildren = false
        todaysTvSeriesViews.offscreenPageLimit = 3
        todaysTvSeriesViews.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        todaysTvSeriesViews.setPageTransformer(comPosPageTarn)
        sliderHandler= Handler()

        sliderRun = Runnable {
            todaysTvSeriesViews.currentItem = todaysTvSeriesViews.currentItem +1
        }
        todaysTvSeriesViews.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRun)
                    sliderHandler.postDelayed(sliderRun,2000)
                    }
                }
            )
    }
    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRun)
    }
    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRun,2000)
    }
    private fun itemSliderView() {
        todayTvSeriesViewModel.todayTvSeriesList.observe(viewLifecycleOwner, Observer {
            todaysTvSeriesAdapter.updateTodaysTvSeriesList(it)
        })
    }
//------------------------------------------2---------------------------------------------------------

    private fun ObserveLiveDataForCategories (){
        searchViewModel.searchTvSeriesList.observe(viewLifecycleOwner, Observer {
            categorieAdapter.updateCategoriData(it)
        })
    }

    private fun ObserveLiveDataForCars (){
        carsViewModel.carsTvSeriesList.observe(viewLifecycleOwner, Observer {
            carsAdapter.updatecarsData(it)
        })
    }

    private fun ObserveLiveDataForTvShows (){
        tvShowSeriesViewModel.tvShowsTvSeriesList.observe(viewLifecycleOwner, Observer {
            tvShowsAdapter.updateTvShowsData(it)
        })
    }

}