package com.example.hubtv.ui.recyclerView_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hubtv.databinding.BannerListBinding
import com.example.hubtv.remote.ModelClass.BannerSection

class BannerListAdapter(
    private val context: Context,
    private val onItemClicked: (BannerSection) -> Unit
) : ListAdapter<BannerSection, BannerListAdapter.ListViewHolder>(DiffUtil()) {

    class ListViewHolder(itemView: BannerListBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val image: ImageView = itemView.bannerImageView

        fun bind(
            data: BannerSection,
            onItemClicked: (BannerSection) -> Unit,
            context: Context
        ) {
            Glide.with(context)
                .load(data.image_path)
                .into(image)

            image.setOnClickListener {
                onItemClicked(data)
            }
        }
    }


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<BannerSection>() {
        override fun areItemsTheSame(
            oldItem: BannerSection,
            newItem: BannerSection
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BannerSection,
            newItem: BannerSection
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            BannerListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data,onItemClicked,context)
    }

}