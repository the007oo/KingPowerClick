package com.phattarapong.kingpowerclick.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.phattarapong.kingpowerclick.R
import com.phattarapong.kingpowerclick.model.PhotoRemoteModel

class PhotosAdapter(var dataList: List<PhotoRemoteModel>) :
    RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    var clickListener : ((PhotoRemoteModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photos, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataList[position])
        holder.itemView.setOnClickListener {
            clickListener?.let {
                it.invoke(dataList[position])
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.textView)

        fun bindData(photoRemoteModel: PhotoRemoteModel) {
            Glide.with(itemView.context)
                .load(photoRemoteModel.thumbnailUrl)
                .error(android.R.drawable.ic_menu_report_image)
                .load(android.R.drawable.ic_menu_report_image)
                .into(imageView)

            textView.text = photoRemoteModel.title
        }
    }

}
