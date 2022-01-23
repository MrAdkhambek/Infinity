package mr.adkhambek.infinity.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import javax.inject.Inject
import javax.inject.Provider

typealias InfinityAdapterLister = (BaseItem?) -> Unit

class InfinityAdapter @Inject constructor(
    baseItemDiffUtil: BaseItemDiffUtil,
    private val creators: Map<Int, @JvmSuppressWildcards Provider<BaseViewHolderProvider>>
) : PagingDataAdapter<BaseItem, BaseVH>(baseItemDiffUtil) {

    var onClickListener: InfinityAdapterLister? = null

    override fun onBindViewHolder(holder: BaseVH, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        val vh = creators[viewType]?.get()?.create(view) ?: throw IllegalArgumentException("Wrong item type")
        vh.itemView.setOnClickListener {
            onClickListener?.invoke(getItem(vh.bindingAdapterPosition))
        }
        return vh
    }

    override fun getItemViewType(position: Int): Int = getItem(position)?.layoutRes ?: throw IllegalArgumentException("Wrong item type")
}


