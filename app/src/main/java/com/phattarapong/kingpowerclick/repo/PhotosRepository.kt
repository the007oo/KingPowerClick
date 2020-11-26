package com.phattarapong.cartrack.register.repo

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.phattarapong.kingpowerclick.database.PhotoDatabase
import com.phattarapong.kingpowerclick.database.asRemoteModel
import com.phattarapong.kingpowerclick.model.PhotoRemoteModel
import com.phattarapong.kingpowerclick.model.asLocalModel
import com.phattarapong.kingpowerclick.network.Response
import com.phattarapong.kingpowerclick.network.getNetworkService
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class PhotosRepository(val lifecycleOwner: LifecycleOwner,val database : PhotoDatabase) {

    fun getPhotos() : LiveData<Response<List<PhotoRemoteModel>>> {
        fetchPhotos()

        val data = MutableLiveData<Response<List<PhotoRemoteModel>>>()
        data.value = Response.Loading

        try {
            database.photoDao.getAllPhoto().observe(lifecycleOwner, {
                data.value = Response.Success(it.asRemoteModel())
            })
        }catch (e : Exception){
            data.value = Response.Error(e)
        }

        return data
    }

    private fun fetchPhotos(){
        getNetworkService().getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<PhotoRemoteModel>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: List<PhotoRemoteModel>) {
                    database.photoDao.insertAll(t.asLocalModel())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : CompletableObserver {
                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onComplete() {

                            }

                            override fun onError(e: Throwable) {
                            }

                        })
                }

                override fun onError(e: Throwable) {

                }

            })
    }
}