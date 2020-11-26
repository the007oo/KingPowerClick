package com.phattarapong.kingpowerclick.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.phattarapong.kingpowerclick.model.PhotoRemoteModel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "photo_table")
data class PhotoLocalModel(

    @ColumnInfo(name = "albumId")
    var albumId: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl: String,


    ) : Parcelable

fun List<PhotoLocalModel>.asRemoteModel(): List<PhotoRemoteModel> {
    return map {
        PhotoRemoteModel(
            it.albumId,
            it.id,
            it.title,
            it.url,
            it.thumbnailUrl
        )
    }
}
