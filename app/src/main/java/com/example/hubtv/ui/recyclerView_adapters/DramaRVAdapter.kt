package com.example.hubtv.ui.recyclerView_adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hubtv.databinding.DramaRvBinding
import com.example.hubtv.remote.ModelClass.Data

class DramaRVAdapter(
    private val context: Context,
    private val onItemClicked: (Data?) -> Unit
) : ListAdapter<Data, DramaRVAdapter.ListViewHolder>(DiffUtil()) {

    class ListViewHolder(itemView: DramaRvBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val image: ImageView = itemView.dramaImageView

        fun bind(
            data: Data,
            onItemClicked: (Data) -> Unit,
            context: Context
        ) {
            Glide.with(context)
                .load(data.parent_poster_for_mobile_apps)
                .into(image)

            image.setOnClickListener {
                onItemClicked(data)
            }
        }
    }


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Data?>() {
        override fun areItemsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem.is_media_published == newItem.is_media_published
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            DramaRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data, onItemClicked, context)

    }
}