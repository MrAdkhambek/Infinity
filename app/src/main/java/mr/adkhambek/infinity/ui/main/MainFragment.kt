package mr.adkhambek.infinity.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import mr.adkhambek.infinity.R
import mr.adkhambek.infinity.databinding.MainFragmentBinding
import mr.adkhambek.infinity.ui.main.adapter.InfinityAdapter
import mr.adkhambek.infinity.ui.main.adapter.TIP_ADVERTISEMENT
import mr.adkhambek.infinity.ui.main.adapter.TIP_VIDEO
import mr.adkhambek.infinity.util.SpacingItemDecorator


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val vm: MainViewModel by viewModels()
    private val vb: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    private fun setupRecycler() = with(vb.main) {
        val adapter = InfinityAdapter()

        val layoutManager = GridLayoutManager(this.context, 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    TIP_ADVERTISEMENT -> 3
                    TIP_VIDEO -> 2
                    else -> 1
                }
            }
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