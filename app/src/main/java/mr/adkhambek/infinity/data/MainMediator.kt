package mr.adkhambek.infinity.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import mr.adkhambek.infinity.ui.main.adapter.BaseItem
import javax.inject.Inject
import kotlin.random.Random

class Repository @Inject constructor() {

    suspend fun getNextPage(key: Int, pageId: Long): List<BaseItem> {
        return (1..20).map { id ->
            when (Random.nextInt(0, 3)) {
                0 -> BaseItem.ImageItem(
                    uniqueId = ((key * 20) + id).toString(),
                    photo = "https://picsum.photos/300/300"
                )
                1 -> BaseItem.VideoItem(
                    uniqueId = ((key * 20) + id).toString(),
                    video = "https://picsum.photos/300/300/?blur=5"
                )
                else -> BaseItem.Advertisement(
                    uniqueId = ((key * 20) + id).toString(),
                    photo = "https://picsum.photos/300/300/?blur=1"
                )
            }
        }
    }
}


@ExperimentalPagingApi
class MainMediator private constructor(
    private val id: Long,
    private val repository: Repository
) : PagingSource<Int, BaseItem>() {

    class Factory @Inject constructor(
        private val repository: Repository
    ) {
        fun create(id: Long): PagingSource<Int, BaseItem> {
            return MainMediator(id, repository)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BaseItem>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseItem> {
        val nextPage = params.key ?: 1
        val response = repository.getNextPage(nextPage, id)

        return LoadResult.Page(
            data = response,
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = nextPage + 1
        )
    }
}