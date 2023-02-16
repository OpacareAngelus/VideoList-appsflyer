package test.videolist.activity.fragmentListVideo.fragmentVideoDetails

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import architecture.BaseFragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import test.videolist.R
import test.videolist.activity.fragmentListVideo.FragmentListVideoViewModel
import test.videolist.activity.fragmentListVideo.fragmentVideoDetails.adapter.RecyclerAdapterVideoCommentsList
import test.videolist.databinding.FragmentVideoDetailsBinding


class FragmentVideoDetails :
    BaseFragment<FragmentVideoDetailsBinding>(FragmentVideoDetailsBinding::inflate),
    Player.Listener {

    private val args: FragmentVideoDetailsArgs by navArgs()
    private val viewModel: FragmentVideoDetailsViewModel by viewModels()
    private val viewModelListVideo: FragmentListVideoViewModel by activityViewModels()
    private var playerExo: ExoPlayer? = null

    private val usersAdapter by lazy {
        RecyclerAdapterVideoCommentsList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapters()
        setListeners()
        setObservers()
    }

    override fun onStart() {
        super.onStart()
        setupPlayer()
        viewModel.loadComments(args.video.commentsList)
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
        viewModel.commentsList.value?.let { viewModelListVideo.saveComments(args.video, it) }
    }

    private fun setAdapters() {
        val recyclerView: RecyclerView = binding.rvVideoComments.apply { adapter = usersAdapter }
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            observeRating()
            btnAddComment.setOnClickListener {
                println(etAddComment.text.toString())
                viewModel.addComment(etAddComment.text.toString())
                println(viewModel.commentsList.value)
            }
            btnFavorite.setOnClickListener {
                if (!args.video.inUserFavorite) {
                    viewModelListVideo.changeFavoriteStatusOfVideo(args.video)
                    btnFavorite.background =
                        AppCompatResources.getDrawable(requireActivity(),R.drawable.ic_fill_heart)
                } else {
                    viewModelListVideo.changeFavoriteStatusOfVideo(args.video)
                    btnFavorite.background =
                        AppCompatResources.getDrawable(requireActivity(),R.drawable.ic_empty_heart)
                }
            }
            if (args.video.inUserFavorite) btnFavorite.background =
                AppCompatResources.getDrawable(requireActivity(),R.drawable.ic_fill_heart)
        }
    }

    private fun observeRating() {
        with(binding) {
            rbVideoRate.rating = args.video.rating
            rbVideoRate.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener()
            { _: RatingBar, fl: Float, _: Boolean ->
                viewModelListVideo.changeVideoRate(args.video, fl)
            }
        }
    }

    private fun setObservers() {
        viewModel.commentsList.observe(viewLifecycleOwner) {
            usersAdapter.submitList(it)
        }
    }

    private fun setupPlayer() {
        playerExo = ExoPlayer.Builder(requireActivity()).build()
        playerExo?.playWhenReady = true
        binding.pvDetailsPlayer.player = playerExo
        val mediaItem = MediaItem.fromUri(args.video.linkVideo)
        playerExo?.setMediaItem(mediaItem)
        viewModel.playbackPosition.observe(this) {
            playerExo?.seekTo(it)
        }
        viewModel.playWhenReady.observe(this) {
            playerExo?.playWhenReady = it
        }
        playerExo?.prepare()
    }

    private fun releasePlayer() {
        playerExo?.let { player ->
            viewModel.setNewPlaybackPosition(player.currentPosition)
            viewModel.setNewPlayWhenReady(player.playWhenReady)
            player.release()
            playerExo = null
        }
    }
}