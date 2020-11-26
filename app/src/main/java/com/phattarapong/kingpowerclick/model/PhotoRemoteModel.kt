package com.phattarapong.kingpowerclick.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.phattarapong.kingpowerclick.database.PhotoLocalModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoRemoteModel(

    @SerializedName("albumId")
    @Expose
    var albumId: String,

    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("url")
    @Expose
    var url: String,

    @SerializedName("thumbnailUrl")
    @Expose
    var thumbnailUrl: String,

    ) : Parcelable

fun List<PhotoRemoteModel>.asLocalModel(): List<PhotoLocalModel> {
    return map {
        PhotoLocalModel(
            it.albumId,
            it.id,
            it.title,
            it.url,
            it.thumbnailUrl
        )
    }
}