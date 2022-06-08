package com.zaniva.restoapp.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrayItem (
    var name: String,
    var price: String,
    var desc: String,
    var amount: String,
    var photo: Int
) : Parcelable
