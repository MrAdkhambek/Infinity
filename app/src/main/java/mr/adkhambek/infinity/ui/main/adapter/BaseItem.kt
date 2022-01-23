package mr.adkhambek.infinity.ui.main.adapter

sealed class BaseItem {

    abstract val uniqueId: String

    data class Advertisement(
        val photo: String,
        override val uniqueId: String,
    ) : BaseItem()

    data class ImageItem(
        val photo: String,
        override val uniqueId: String,
    ) : BaseItem()

    data class VideoItem(
        val video: String,
        override val uniqueId: String,
    ) : BaseItem()
}