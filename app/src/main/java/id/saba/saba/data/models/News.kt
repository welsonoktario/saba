package id.saba.saba.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val id: Int,
    val user: User,
    val judul: String,
    val gambar: String,
    val tanggal: String,
    val deskripsi: String,
    var bookmarked: Boolean = false
) : Parcelable
