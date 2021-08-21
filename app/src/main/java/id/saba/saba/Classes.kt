package id.saba.saba

data class SliderModal(val title: String,val heading: String, val img: Int){
}
data class ClassEvent(val name: String, val number: String, val photo: Int) {
}
data class ClassNews(val username: String, val judul: String, val gambar: String, val tanggal: String, val berita: String = "") {
}
data class ClassTrendingForum(val name: String, val tanggal: String, val deskripsi: String) {
}
data class ClassNotifikasi(val id: String, val keterangan: String, val tanggal: String, val photo: Int) {
}