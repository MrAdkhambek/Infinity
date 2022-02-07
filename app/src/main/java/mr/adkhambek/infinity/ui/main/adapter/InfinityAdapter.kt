package mr.adkhambek.infinity.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import mr.adkhambek.infinity.R
import mr.adkhambek.infinity.databinding.ItemAdvertisementBinding
import mr.adkhambek.infinity.databinding.ItemImageBinding
import mr.adkhambek.infinity.databinding.ItemVedioBinding


internal const val TIP_ADVERTISEMENT = R.layout.item_advertisement
internal const val TIP_VIDEO = R.layout.item_vedio
internal const val TIP_IMAGE = R.layout.item_image


class InfinityAdapter : PagingDataAdapter<BaseItem, BaseVH>(BaseItemDiffUtil()) {

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            TIP_VIDEO -> VideoVH(ItemVedioBinding.bind(view))
            TIP_IMAGE -> ImageVH(ItemImageBinding.bind(view))
            TIP_ADVERTISEMENT -> AdvertisementVH(ItemAdvertisementBinding.bind(view))
            else -> throw IllegalArgumentException("Wrong item type")
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is BaseItem.ImageItem -> TIP_IMAGE
        is BaseItem.VideoItem -> TIP_VIDEO
        is BaseItem.Advertisement -> TIP_ADVERTISEMENT
        else -> throw IllegalArgumentException("Wrong item type")
    }
}


