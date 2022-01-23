@file:Suppress("FunctionName")

package mr.adkhambek.infinity.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import mr.adkhambek.infinity.ui.main.adapter.BaseItem.*


fun BaseItemDiffUtil(
    imageItemDiffUtil: DiffUtil.ItemCallback<ImageItem> = ImageItemDiffUtil(),
    videoItemDiffUtil: DiffUtil.ItemCallback<VideoItem> = VideoItemDiffUtil(),
    advertisementDiffUtil: DiffUtil.ItemCallback<Advertisement> = AdvertisementDiffUtil(),
) = object : DiffUtil.ItemCallback<BaseItem>() {
    override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean = oldItem.uniqueId == newItem.uniqueId

    override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean = when {
        (oldItem is Advertisement && newItem is Advertisement) -> advertisementDiffUtil.areContentsTheSame(oldItem, newItem)
        (oldItem is ImageItem && newItem is ImageItem) -> imageItemDiffUtil.areContentsTheSame(oldItem, newItem)
        (oldItem is VideoItem && newItem is VideoItem) -> videoItemDiffUtil.areContentsTheSame(oldItem, newItem)
        else -> false
    }
}

fun AdvertisementDiffUtil() = object : DiffUtil.ItemCallback<Advertisement>() {
    override fun areItemsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean = oldItem.uniqueId == newItem.uniqueId

    override fun areContentsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean =
        oldItem.photo == newItem.photo
}

fun ImageItemDiffUtil() = object : DiffUtil.ItemCallback<ImageItem>() {
    override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean = oldItem.uniqueId == newItem.uniqueId

    override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
        oldItem.photo == newItem.photo
}

fun VideoItemDiffUtil() = object : DiffUtil.ItemCallback<VideoItem>() {
    override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean = oldItem.uniqueId == newItem.uniqueId

    override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean =
        oldItem.video == newItem.video
}
