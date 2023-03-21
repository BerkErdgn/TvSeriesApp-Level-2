package com.berke.mytvseriesapplevel2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.mytvseriesapplevel2.models.allTvModels.AllTvSeriesModels
import com.berke.mytvseriesapplevel2.services.AllTvSeriesApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AllTvSeriesViewModel : ViewModel(){
    private val disposable = CompositeDisposable()
    private val allTvSeriesApiServices = AllTvSeriesApiServices ()
    var allTvSeriesList = MutableLiveData<AllTvSeriesModels> ()


    fun refreshAllData(){
        return getAllDataFromApi()
    }

    private fun getAllDataFromApi(){
        disposable.add(
            allTvSeriesApiServices.getAllData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object  : DisposableSingleObserver<AllTvSeriesModels>(){
                    override fun onSuccess(t: AllTvSeriesModels) {
                        allTvSeriesList.value= t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

}