package com.phattarapong.kingpowerclick.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.phattarapong.cartrack.register.repo.PhotosRepository
import com.phattarapong.kingpowerclick.model.PhotoRemoteModel
import com.phattarapong.kingpowerclick.network.Response
import io.reactivex.disposables.CompositeDisposable

class PhotosViewModel(photosRepository: PhotosRepository) : ViewModel() {

    private var compositeDisposable: CompositeDisposable
    val photos : LiveData<Response<List<PhotoRemoteModel>>> = photosRepository.getPhotos()

    init {
        compositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}