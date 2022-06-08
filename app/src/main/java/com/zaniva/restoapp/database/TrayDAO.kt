package com.zaniva.restoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TrayDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(tray: Tray)

    @Update
    fun update(tray: Tray)

    @Delete
    fun delete(tray: Tray)

    @Query("SELECT * from tray ORDER BY id ASC")
    fun getAllTray(): LiveData<List<Tray>>

}