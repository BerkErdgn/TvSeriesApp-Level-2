package com.berke.mytvseriesapplevel2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.mytvseriesapplevel2.models.oneEpisodesModels.OneEpisodesModel
import com.berke.mytvseriesapplevel2.services.OneEpisodeApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class OneEpisodeViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    private val oneEpisodeApiServices = OneEpisodeApiServices()
    val oneEpisodesList = MutableLiveData<OneEpisodesModel>()


    fun refreshOneEpisodeData(idEpisodes: Int){
        getOneEpisodeDataFromApi(idEpisodes)
    }

    private fun getOneEpisodeDataFromApi(idEpisodes:Int){
        disposable.add(
            oneEpisodeApiServices.getOneEpisode(idEpisodes)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<OneEpisodesModel>(){
                    override fun onSuccess(t: OneEpisodesModel) {
                        oneEpisodesList.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

}