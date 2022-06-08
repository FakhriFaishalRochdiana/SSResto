package com.zaniva.restoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tray::class], version = 1)
abstract class TrayRoomDatabase : RoomDatabase(){
    abstract fun trayDao(): TrayDAO
    companion object {
        @Volatile
        private var INSTANCE: TrayRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): TrayRoomDatabase {
            if (INSTANCE == null) {
                synchronized(TrayRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TrayRoomDatabase::class.java, "tray_database")
                        .build()
                }
            }
            return INSTANCE as TrayRoomDatabase
        }
    }
}