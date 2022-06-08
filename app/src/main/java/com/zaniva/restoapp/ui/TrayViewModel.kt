package com.zaniva.restoapp.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zaniva.restoapp.database.Tray
import com.zaniva.restoapp.dataclass.Menu
import com.zaniva.restoapp.repository.TrayRepository

class TrayViewModel(application: Application) : ViewModel() {
    private val mTrayRepository: TrayRepository = TrayRepository(application)
    fun insert(tray: Tray) {
        mTrayRepository.insert(tray)
    }
    fun update(tray: Tray) {
        mTrayRepository.update(tray)
    }
    fun delete(tray: Tray) {
        mTrayRepository.delete(tray)
    }
}