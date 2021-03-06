package mr.adkhambek.infinity.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import mr.adkhambek.infinity.data.MainMediator
import mr.adkhambek.infinity.ui.main.adapter.BaseItem
import javax.inject.Inject


@HiltViewModel
class MainViewModel @ExperimentalPagingApi
@Inject constructor(
    private val mainMediatorFactory: MainMediator.Factory,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val baseItems: Flow<PagingData<BaseItem>> = Pager(PagingConfig(pageSize = 20)) {
        mainMediatorFactory.create(1L)
    }.flow.cachedIn(viewModelScope)
}