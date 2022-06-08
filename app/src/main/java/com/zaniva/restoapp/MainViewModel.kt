package com.zaniva.restoapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zaniva.restoapp.database.Tray
import com.zaniva.restoapp.repository.TrayRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mTrayRepository: TrayRepository = TrayRepository(application)

    fun getAllTray(): LiveData<List<Tray>> = mTrayRepository.getAllTray()
}