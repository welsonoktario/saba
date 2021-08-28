package id.saba.saba.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Scholarship(val id: Int, val user: User, val judul: String, val deskripsi: String) :
    Parcelable
