package com.phattarapong.kingpowerclick.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.phattarapong.cartrack.register.repo.PhotosRepository
import com.phattarapong.kingpowerclick.database.PhotoDatabase


/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class PhotosViewModelFactory(val lifecycleOwner: LifecycleOwner,val database: PhotoDatabase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            return PhotosViewModel(
                PhotosRepository(lifecycleOwner,database)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}