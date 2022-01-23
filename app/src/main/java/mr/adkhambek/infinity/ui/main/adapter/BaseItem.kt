package mr.adkhambek.infinity.ui.main.adapter

import mr.adkhambek.infinity.R

sealed class BaseItem {

    abstract val layoutRes: Int

    data class Advertisement(
        val photo: String,
        val uniqueId: String,
    ) : BaseItem() {

        override val layoutRes: Int get() = R.layout.item_advertisement
    }

    data class ImageItem(
        val photo: String,
        val uniqueId: String,
    ) : BaseItem() {

        override val layoutRes: Int get() = R.layout.item_image
    }

    data class VideoItem(
        val video: String,
        val uniqueId: String,
    ) : BaseItem() {

        override val layoutRes: Int get() = R.layout.item_vedio
    }
}