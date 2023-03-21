package com.berke.mytvseriesapplevel2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.mytvseriesapplevel2.models.crewModels.CrewModel
import com.berke.mytvseriesapplevel2.services.CrewApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CrewViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    private val crewApiServices = CrewApiServices()
    val crewList = MutableLiveData<CrewModel>()

    fun refreshCrewData (idTvSeries: Int){
       return getCrewDataFromApi(idTvSeries)
    }

    private fun getCrewDataFromApi(idTvSeries: Int){
        disposable.add(
            crewApiServices.getCrewData(idTvSeries)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CrewModel>(){
                    override fun onSuccess(t: CrewModel) {
                        crewList.value = t
                    }

                    override fun onError(e: Throwable) {
                       e.printStackTrace()
                    }

                })
        )
    }
}