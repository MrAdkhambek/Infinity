package mr.adkhambek.infinity.data

import mr.adkhambek.infinity.ui.main.adapter.BaseItem
import javax.inject.Inject

class Repository @Inject constructor() {

    suspend fun loadWithoutPaging() : List<BaseItem> {
        return (1..10).map {
            BaseItem.ImageItem(
                uniqueId = ((it * 20) + it - 1000).toString(),
                photo = "https://picsum.photos/300/300"
            )
        }
    }
}