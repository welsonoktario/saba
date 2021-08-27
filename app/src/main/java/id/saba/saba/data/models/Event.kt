package id.saba.saba.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Event(
    val id: Int,
    val gambar: String,
    val title: String,
    val headline: String,
    val user: User,
    val deskripsi: String,
    val lokasi: String,
    val tanggalMulai: String,
    val tanggalAkhir: String,
    val waktuMulai: Int,
    val waktuAkhir: Int,
    val hargaEarly: Int,
    val hargaRegular: Int
) : Parcelable {
    fun textTanggal(): String {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        calendar.time = sdf.parse(tanggalMulai)
        var mulai = calendar.get(Calendar.DAY_OF_MONTH)

        calendar.time = sdf.parse(tanggalAkhir)
        val akhir = calendar.get(Calendar.DAY_OF_MONTH)
        val bulan = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())

        return "$mulai - $akhir $bulan"
    }

    fun textWaktu(): String {
        var mulai: String = if (waktuMulai > 12) {
            "${waktuMulai - 12} pm"
        } else {
            "$waktuMulai am"
        }

        var akhir = if (waktuAkhir > 12) {
            "${waktuAkhir - 12} pm"
        } else {
            "$waktuAkhir am"
        }

        return "Opentime: $mulai - $akhir"
    }

    fun textEarly() = "Early Bid: Rp $hargaEarly"

    fun textRegular() = "Regular: Rp $hargaRegular"
}
