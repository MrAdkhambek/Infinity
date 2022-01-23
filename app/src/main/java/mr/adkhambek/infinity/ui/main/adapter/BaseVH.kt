package mr.adkhambek.infinity.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import mr.adkhambek.infinity.databinding.ItemAdvertisementBinding
import mr.adkhambek.infinity.databinding.ItemImageBinding
import mr.adkhambek.infinity.databinding.ItemVedioBinding
import mr.adkhambek.infinity.util.load

abstract class BaseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(baseItem: BaseItem?)
}


class AdvertisementVH(private val binding: ItemAdvertisementBinding) : BaseVH(binding.root) {
    override fun bind(baseItem: BaseItem?) {
        val item = baseItem as BaseItem.Advertisement?

        binding.imageView.load(item?.photo)
    }
}

class ImageVH(private val binding: ItemImageBinding) : BaseVH(binding.root) {
    override fun bind(baseItem: BaseItem?) {
        val item = baseItem as BaseItem.ImageItem?

        binding.root.load(item?.photo)
    }
}

class VideoVH(private val binding: ItemVedioBinding) : BaseVH(binding.root) {
    override fun bind(baseItem: BaseItem?) {
        val item = baseItem as BaseItem.VideoItem?

        binding.imageView.load(item?.video)
    }
}