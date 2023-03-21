package com.berke.mytvseriesapplevel2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModels
import com.berke.mytvseriesapplevel2.services.TodayTvSeriesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TodayTvSeriesViewModel : ViewModel(){

    private val disposable = CompositeDisposable()
    private val todayTvSeriesApiService = TodayTvSeriesApiService()
    val todayTvSeriesList = MutableLiveData<TodaysTvSeriesModels>()


    fun refreshTodayData(date: String){
        getTodayDataFromApi(date)
    }


    private fun getTodayDataFromApi(date : String){
        disposable.add(
            todayTvSeriesApiService.getTodayData(date)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TodaysTvSeriesModels>(){
                    override fun onSuccess(t: TodaysTvSeriesModels) {
                        todayTvSeriesList.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }


}