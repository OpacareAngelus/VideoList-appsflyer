package test.videolist.activity.fragmentListVideo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import architecture.BaseFragment
import test.videolist.R
import test.videolist.activity.adapter.RecyclerAdapterVideoList
import test.videolist.databinding.FragmentListVideoBinding
import test.videolist.model.VideoItem

class FragmentListVideo : BaseFragment<FragmentListVideoBinding>(FragmentListVideoBinding::inflate),
    RecyclerAdapterVideoList.ControllerListVideo {
    private val viewModel: FragmentListVideoViewModel by activityViewModels()

    private val usersAdapter by lazy {
        RecyclerAdapterVideoList(controllerListVideo = this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        setListeners()
        setObservers()
    }

    private fun setAdapters() {
        val recyclerView: RecyclerView = binding.rvVideo.apply { adapter = usersAdapter }
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), ROW_AMOUNT)
    }

    private fun setListeners() {
        with(binding) {
            btnLens.setOnClickListener {
                findNavController().navigate(FragmentListVideoDirections.actionFragmentListVideoToFragmentWebView())
            }
            /* TODO If u want to back searching - delete the listener options upper that
                and uncomment this.
            btnLens.setOnClickListener {
                viewModel.searchVideoWithName(etSearching.text.toString())
            }*/
            btnMushroom.setOnClickListener {
                viewModel.filterListByCategory(requireActivity().getString(R.string.category_mushroom))
            }
            btnAnimal.setOnClickListener {
                viewModel.filterListByCategory(requireActivity().getString(R.string.category_animals))
            }
            btnTermite.setOnClickListener {
                viewModel.filterListByCategory(requireActivity().getString(R.string.category_termite))
            }
            btnMyFavorite.setOnClickListener {
                viewModel.filterListByMyFavorite()
            }
        }
    }

    override fun openVideoInPlayer(video: VideoItem) {
        findNavController().navigate(
            FragmentListVideoDirections.actionFragmentListVideoToFragmentVideoDetails(video)
        )
    }

    private fun setObservers() {
        viewModel.listVideoItems.observe(viewLifecycleOwner) {
            usersAdapter.submitList(it)
        }
    }

    companion object {
        const val ROW_AMOUNT = 2
    }
}