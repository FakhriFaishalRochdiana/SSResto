package com.zaniva.restoapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.zaniva.restoapp.database.Tray
import com.zaniva.restoapp.database.TrayDAO
import com.zaniva.restoapp.database.TrayRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TrayRepository(application: Application) {
    private val mTrayDao: TrayDAO
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = TrayRoomDatabase.getDatabase(application)
        mTrayDao = db.trayDao()
    }
    fun getAllTray(): LiveData<List<Tray>> = mTrayDao.getAllTray()
    fun insert(tray: Tray) {
        executorService.execute { mTrayDao.insert(tray) }
    }
    fun delete(tray: Tray) {
        executorService.execute { mTrayDao.delete(tray) }
    }
    fun update(tray: Tray) {
        executorService.execute { mTrayDao.update(tray) }
    }
}