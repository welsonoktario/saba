package id.saba.saba.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(val id: Int, val gambar: String, val title: String, val headline: String, val user: User, val deskripsi: String) : Parcelable
