package mr.adkhambek.infinity.di.adapter

import android.view.View
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import mr.adkhambek.infinity.R
import mr.adkhambek.infinity.databinding.ItemAdvertisementBinding
import mr.adkhambek.infinity.databinding.ItemImageBinding
import mr.adkhambek.infinity.databinding.ItemVedioBinding
import mr.adkhambek.infinity.ui.main.adapter.AdvertisementVH
import mr.adkhambek.infinity.ui.main.adapter.BaseViewHolderProvider
import mr.adkhambek.infinity.ui.main.adapter.ImageVH
import mr.adkhambek.infinity.ui.main.adapter.VideoVH


@Module
@InstallIn(FragmentComponent::class)
object BindModule {

    @Provides
    @IntoMap
    @IntKey(R.layout.item_advertisement)
    fun bindAdvertisementVH(): BaseViewHolderProvider = object : BaseViewHolderProvider {
        override fun create(view: View): AdvertisementVH {
            return AdvertisementVH(ItemAdvertisementBinding.bind(view))
        }
    }

    @Provides
    @IntoMap
    @IntKey(R.layout.item_vedio)
    fun bindVideoVH(): BaseViewHolderProvider = object : BaseViewHolderProvider {
        override fun create(view: View): VideoVH {
            return VideoVH(ItemVedioBinding.bind(view))
        }
    }

    @Provides
    @IntoMap
    @IntKey(R.layout.item_image)
    fun bindImageVH(): BaseViewHolderProvider = object : BaseViewHolderProvider {
        override fun create(view: View): ImageVH {
            return ImageVH(ItemImageBinding.bind(view))
        }
    }
}
