package mr.adkhambek.infinity.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import mr.adkhambek.infinity.R
import mr.adkhambek.infinity.databinding.MainFragmentBinding
import mr.adkhambek.infinity.ui.main.adapter.InfinityAdapter
import mr.adkhambek.infinity.util.SpacingItemDecorator
import mr.adkhambek.infinity.util.SpannedGridLayoutManager
import mr.adkhambek.infinity.util.SpannedGridLayoutManager.SpanInfo
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    @Inject lateinit var infinityAdapterProvider: Lazy<InfinityAdapter>

    private val vm: MainViewModel by viewModels()
    private val vb: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    private fun setupRecycler() = with(vb.main) {
        val layoutManager = SpannedGridLayoutManager(
            { position ->
                val x = position / 11
                when {
                    (position % 11 == 0 && x % 2 == 1) || (position % 11 == 2 && x % 2 == 0) -> {
                        SpanInfo(1, 2)
                    }
                    position % 11 == 6 -> {
                        SpanInfo(2, 2)
                    }
                    else -> {
                        SpanInfo(1, 1)
                    }
                }
            },
            3,  // number of columns
            1f // how big is default item
        )

        val adapter = infinityAdapterProvider.get()
        adapter.onClickListener = {
            Toast.makeText(this.context, it?.toString().orEmpty(), Toast.LENGTH_SHORT).show()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.baseItems.collectLatest(adapter::submitData)
        }

        this.addItemDecoration(SpacingItemDecorator((resources.displayMetrics.density * 0.5).toInt()))
        this.layoutManager = layoutManager
        this.adapter = adapter
    }

    override fun onDestroyView() {
        vb.main.adapter = null
        super.onDestroyView()
    }
}