package com.berke.mytvseriesapplevel2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.mytvseriesapplevel2.models.searchModels.SearchTvSeriesModels
import com.berke.mytvseriesapplevel2.services.SearchTvSeriesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel: ViewModel() {


    private val disposable = CompositeDisposable()
    private val searchTvSeriesApiService = SearchTvSeriesApiService()
    val searchTvSeriesList = MutableLiveData<SearchTvSeriesModels>()



    fun refreshSearchData(q:String){
        getSearchDaraFromApi(q)
    }

    private fun getSearchDaraFromApi(q:String){
        disposable.add(
            searchTvSeriesApiService.getSearchData(q)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchTvSeriesModels>(){
                    override fun onSuccess(t: SearchTvSeriesModels) {
                       searchTvSeriesList.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }
}