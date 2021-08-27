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
    val tanggalMulai: String,
    val tanggalAkhir: String,
    val waktuMulai: String,
    val waktuAkhir: String,
    val hargaEarly: Int,
    val hargaRegular: Int
) : Parcelable {
    fun textTanggal(): String {
        val sdfHari = SimpleDateFormat("dd", Locale.getDefault())
        val sdfBulan = SimpleDateFormat("MM", Locale.getDefault())

        val mulai = sdfHari.format(tanggalMulai)
        val akhir = sdfHari.format(tanggalAkhir)
        val bulan = sdfBulan.get(Calendar.MONTH)

        return "$mulai - $akhir $bulan"
    }
}
