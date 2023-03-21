package com.berke.mytvseriesapplevel2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.mytvseriesapplevel2.models.searchModels.SearchTvSeriesModels
import com.berke.mytvseriesapplevel2.services.SearchTvSeriesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TvShowsTvSeriesViewModel : ViewModel() {


    private val disposable = CompositeDisposable()
    private val searchTvSeriesApiService = SearchTvSeriesApiService()
    val tvShowsTvSeriesList = MutableLiveData<SearchTvSeriesModels>()



    fun refreshTvShowsData(q:String){
        getTvShowsFromApi(q)
    }

    private fun getTvShowsFromApi(q:String){
        disposable.add(
            searchTvSeriesApiService.getSearchData(q)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchTvSeriesModels>(){
                    override fun onSuccess(t: SearchTvSeriesModels) {
                        tvShowsTvSeriesList.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }
}