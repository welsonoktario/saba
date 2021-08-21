package id.saba.saba.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.saba.saba.*
import com.squareup.picasso.Picasso
import id.saba.saba.ClassEvent
import id.saba.saba.ClassNews
import id.saba.saba.ClassNotifikasi
import id.saba.saba.ClassTrendingForum
import kotlinx.android.synthetic.main.list_highlightpromosi.view.*
import kotlinx.android.synthetic.main.list_news.view.*
import kotlinx.android.synthetic.main.list_notifikasi.view.*
import kotlinx.android.synthetic.main.list_forum.view.*

class NewsHolder(view: View) : RecyclerView.ViewHolder(view){
    private val judul = view.newsJudul
    private val username = view.newsUser
    private val tanggal = view.newsTanggal
    private val gambar = view.newsImg

    fun bindNews(news: ClassNews){
        this.judul.text = news.judul
        this.username.text = news.username
        this.tanggal.text = news.tanggal
        Picasso.get().load(news.gambar).into(gambar)
    }
}

class NewsAdapter(private val news: List<ClassNews>, private val listener: (ClassNews) -> Unit) : RecyclerView.Adapter<NewsHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): NewsHolder {
        return NewsHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.list_news, viewGroup, false))
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bindNews(news[position])
        holder.itemView.setOnClickListener { listener(news[position]) }
    }
}

class EventHolder(view: View) : RecyclerView.ViewHolder(view){
    private val nameData = view.textViewNamaEvent_1
    private var numberData = ""

    fun bindEvent(event: ClassEvent){
        numberData = event.number
        nameData.text = event.name + " " + numberData
    }
}

class EventAdapter(private val events: List<ClassEvent>, private val listener: (ClassEvent) -> Unit) : RecyclerView.Adapter<EventHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): EventHolder {
        return EventHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.list_highlightpromosi, viewGroup, false))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bindEvent(events[position])
        holder.itemView.setOnClickListener { listener(events[position]) }
    }
}

//===================================================================================================================================================================
class TrendingForumHolder(view: View) : RecyclerView.ViewHolder(view){
    private val nameData = view.textViewNamaPenggunaTrendingForum_1
    private var tanggalData = view.textViewTanggalTrendingForum_1
    private var deskripsiData = view.textViewDeskripsiTrendingForum_1

    fun bindTrendingForum(forum: ClassTrendingForum){
        nameData.text = forum.name
        tanggalData.text = forum.tanggal
        deskripsiData.text = forum.deskripsi
    }
}

class TrendingForumAdapter(private val forums: List<ClassTrendingForum>, private val listener: (ClassTrendingForum) -> Unit) : RecyclerView.Adapter<TrendingForumHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): TrendingForumHolder {
        return TrendingForumHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.list_forum, viewGroup, false))
    }

    override fun getItemCount(): Int = forums.size

    override fun onBindViewHolder(holder: TrendingForumHolder, position: Int) {
        holder.bindTrendingForum(forums[position])
        holder.itemView.setOnClickListener { listener(forums[position]) }
    }
}
//===================================================================================================================================================================
class NotifikasiHolder(view: View) : RecyclerView.ViewHolder(view){
    private var tanggalData = ""
    private var deskripsiData = view.textViewKeteranganNotifikasi_1

    fun bindNotifikasi(notifikasi: ClassNotifikasi){
        tanggalData = notifikasi.tanggal
        deskripsiData.text = notifikasi.keterangan + ". " + tanggalData + "d"
    }
}

class NotifikasiAdapter(private val notifikasi: List<ClassNotifikasi>, private val listener: (ClassNotifikasi) -> Unit) : RecyclerView.Adapter<NotifikasiHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): NotifikasiHolder {
        return NotifikasiHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.list_notifikasi, viewGroup, false))
    }

    override fun getItemCount(): Int = notifikasi.size

    override fun onBindViewHolder(holder: NotifikasiHolder, position: Int) {
        holder.bindNotifikasi(notifikasi[position])
        holder.itemView.setOnClickListener { listener(notifikasi[position]) }
    }
}
//===================================================================================================================================================================