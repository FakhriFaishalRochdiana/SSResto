package com.zaniva.restoapp.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu (
    var name: String,
    var price: String,
    var desc: String,
    var photo: Int
) : Parcelable