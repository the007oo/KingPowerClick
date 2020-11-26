package com.phattarapong.kingpowerclick.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "photo_database"

@Database(entities = [PhotoLocalModel::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {

    abstract val photoDao: PhotoDao

    companion object {

        @Volatile
        private var INSTANCE: PhotoDatabase? = null

        fun getInstance(context: Context): PhotoDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PhotoDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
