package com.berke.mytvseriesapplevel2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.mytvseriesapplevel2.models.episodesModels.EpisodesModel
import com.berke.mytvseriesapplevel2.services.EpisodesApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class EpisodesViewModel: ViewModel() {

    private  val disposable = CompositeDisposable()
    private val episodesApiServices = EpisodesApiServices()
    val episodesList = MutableLiveData<EpisodesModel>()

    fun refreshEpisodesData(idTvSeries: Int){
        getEpisodesDataFromApi(idTvSeries)
    }

    private fun getEpisodesDataFromApi(idTvSeries: Int){
        disposable.add(
            episodesApiServices.getEpisodesData(idTvSeries)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<EpisodesModel>(){
                    override fun onSuccess(t: EpisodesModel) {
                        episodesList.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }
}