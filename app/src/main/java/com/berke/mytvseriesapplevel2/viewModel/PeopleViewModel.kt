package com.berke.mytvseriesapplevel2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.mytvseriesapplevel2.models.peopleModels.PeopleModel
import com.berke.mytvseriesapplevel2.services.PeopleApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PeopleViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    private val peopleApiServices = PeopleApiServices()
    val peopleList = MutableLiveData<PeopleModel>()

    fun refreshPeopleData(idPeople:Int){
        getPeopleDataFromApi(idPeople)
    }

    private fun getPeopleDataFromApi(idPeople:Int){
        disposable.add(
            peopleApiServices.getPeopleData(idPeople)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PeopleModel>(){
                    override fun onSuccess(t: PeopleModel) {
                        peopleList.value = t
                    }

                    override fun onError(e: Throwable) {
                       e.printStackTrace()
                    }

                })
        )
    }
}