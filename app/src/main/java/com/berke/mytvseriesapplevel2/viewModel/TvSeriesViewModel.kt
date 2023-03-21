package com.berke.mytvseriesapplevel2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.mytvseriesapplevel2.models.tvSeriesModels.TvSeriesModels
import com.berke.mytvseriesapplevel2.services.TvSeriesApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TvSeriesViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val tvSeriesApiServices=  TvSeriesApiServices()
    val tvSeriesList = MutableLiveData<TvSeriesModels>()


    fun refreshTvSeriesDat (idTvSeries: Int){
        getTvSeriesDataFromApi(idTvSeries)
    }

    private fun getTvSeriesDataFromApi (idTvSeries:Int){
        disposable.add(
            tvSeriesApiServices.getTvSeriesData(idTvSeries)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TvSeriesModels>(){
                    override fun onSuccess(t: TvSeriesModels) {
                        tvSeriesList.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

}