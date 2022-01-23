@file:Suppress("FunctionName")

package mr.adkhambek.infinity.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import mr.adkhambek.infinity.ui.main.adapter.BaseItem.*
import javax.inject.Inject


class BaseItemDiffUtil @Inject constructor(
    private val imageItemDiffUtil: ImageItemDiffUtil,
    private val videoItemDiffUtil: VideoItemDiffUtil,
    private val advertisementDiffUtil: AdvertisementDiffUtil,
) : DiffUtil.ItemCallback<BaseItem>() {

    override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean = when {
        (oldItem is Advertisement && newItem is Advertisement) -> advertisementDiffUtil.areItemsTheSame(oldItem, newItem)
        (oldItem is ImageItem && newItem is ImageItem) -> imageItemDiffUtil.areItemsTheSame(oldItem, newItem)
        (oldItem is VideoItem && newItem is VideoItem) -> videoItemDiffUtil.areItemsTheSame(oldItem, newItem)
        else -> false
    }

    override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean = when {
        (oldItem is Advertisement && newItem is Advertisement) -> advertisementDiffUtil.areContentsTheSame(oldItem, newItem)
        (oldItem is ImageItem && newItem is ImageItem) -> imageItemDiffUtil.areContentsTheSame(oldItem, newItem)
        (oldItem is VideoItem && newItem is VideoItem) -> videoItemDiffUtil.areContentsTheSame(oldItem, newItem)
        else -> false
    }
}

class AdvertisementDiffUtil @Inject constructor() : DiffUtil.ItemCallback<Advertisement>() {
    override fun areItemsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean = oldItem.uniqueId == newItem.uniqueId

    override fun areContentsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean =
        oldItem.photo == newItem.photo
}

class ImageItemDiffUtil @Inject constructor() : DiffUtil.ItemCallback<ImageItem>() {
    override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean = oldItem.uniqueId == newItem.uniqueId

    override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
        oldItem.photo == newItem.photo
}

class VideoItemDiffUtil @Inject constructor() : DiffUtil.ItemCallback<VideoItem>() {
    override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean = oldItem.uniqueId == newItem.uniqueId

    override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean =
        oldItem.video == newItem.video
}
