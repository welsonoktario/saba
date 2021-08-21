package id.saba.saba.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderViewAdapter
import android.widget.Toast
import id.saba.saba.R
import id.saba.saba.SliderModal
import kotlinx.android.synthetic.main.slider_layout.view.*
import org.jetbrains.anko.backgroundResource

class SliderAdapter(private val context: Context, sliderModalArrayList: ArrayList<SliderModal>) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    private var data = sliderModalArrayList

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, parent, false)

        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem = data[position]
        viewHolder.imgBackground.backgroundResource = sliderItem.img
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getCount() = data.size

    class SliderAdapterVH(itemView: View): ViewHolder(itemView) {
        val imgBackground: ImageView = itemView.idIV
    }
}