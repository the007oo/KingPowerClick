package com.phattarapong.kingpowerclick.database

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photoLocal: PhotoLocalModel): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photoLocals: List<PhotoLocalModel>) : Completable

    @Update
    fun update(photoLocal: PhotoLocalModel)

    @Delete
    fun deletePhoto(photoLocal: PhotoLocalModel)

    @Query("SELECT * from photo_table WHERE id =:id")
    fun getPhotoById(id: String): Single<PhotoLocalModel>

    @Query("SELECT * from photo_table")
    fun getAllPhoto(): LiveData<List<PhotoLocalModel>>

    @Query("DELETE FROM photo_table")
    fun deleteTable()
}
