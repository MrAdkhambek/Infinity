package mr.adkhambek.infinity.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import mr.adkhambek.infinity.ui.main.adapter.BaseItem
import javax.inject.Inject
import kotlin.random.Random


@ExperimentalPagingApi
class MainMediator private constructor() : PagingSource<Int, BaseItem>() {

    class Factory @Inject constructor() {
        fun create(): PagingSource<Int, BaseItem> {
            return MainMediator()
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BaseItem>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseItem> {
        val nextPage = params.key ?: 1

        val response = (1..20).map { id ->
            when (Random.nextInt(0, 3)) {
                0 -> BaseItem.ImageItem(
                    uniqueId = ((nextPage * 20) + id).toString(),
                    photo = "https://picsum.photos/300/300"
                )
                1 -> BaseItem.VideoItem(
                    uniqueId = ((nextPage * 20) + id).toString(),
                    video = "https://picsum.photos/300/300/?blur=5"
                )
                else -> BaseItem.Advertisement(
                    uniqueId = ((nextPage * 20) + id).toString(),
                    photo = "https://picsum.photos/300/300/?blur=1"
                )
            }
        }

        return LoadResult.Page(
            data = response,
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = nextPage + 1
        )
    }
}