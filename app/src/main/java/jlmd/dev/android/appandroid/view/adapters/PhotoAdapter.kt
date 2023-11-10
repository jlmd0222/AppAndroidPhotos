package jlmd.dev.android.appandroid.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jlmd.dev.android.appandroid.view.model.Photo
import jlmd.dev.android.appandroid.R

interface OnItemClickListener {
    fun onDeleteItemClick(photo: Photo, position: Int)
}

class PhotoAdapter(
    private val context: Context,
    private val photoList: ArrayList<Photo>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView: ImageView = itemView.findViewById(R.id.image)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val deleteImageView: ImageView = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.list_item_photo_view, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photoList[position]

        holder.titleTextView.text = photo.title

        Glide.with(context)
            .load(photo.thumbnailUrl)
            .into(holder.photoImageView)

        holder.deleteImageView.setOnClickListener {
            itemClickListener.onDeleteItemClick(photo, position)
        }
    }

    override fun getItemCount(): Int = photoList.size

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(position: Int) {
        if (position >= 0 && position < photoList.size) {
            photoList.removeAt(position)
            notifyDataSetChanged()
        }
    }
}