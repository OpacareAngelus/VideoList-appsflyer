package test.videolist.activity.fragmentListVideo.fragmentVideoDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.videolist.model.Comment

class FragmentVideoDetailsViewModel : ViewModel() {

    private val _playbackPosition = MutableLiveData<Long>()
    val playbackPosition: LiveData<Long> = _playbackPosition

    private val _playWhenReady = MutableLiveData<Boolean>()
    val playWhenReady: LiveData<Boolean> = _playWhenReady

    private val _commentsList = MutableLiveData<List<Comment>>()
    val commentsList: LiveData<List<Comment>> = _commentsList

    init {
        _playbackPosition.value = 0L
        _playWhenReady.value = true
        _commentsList.value = listOf()
    }

    fun setNewPlaybackPosition(time: Long) = _playbackPosition.postValue(time)

    fun setNewPlayWhenReady(boolean: Boolean) = _playWhenReady.postValue(boolean)

    fun addComment(commentText: String) {
        _commentsList.value = _commentsList.value?.plus(Comment(commentText))
    }

    fun loadComments(listComments: List<Comment>) = _commentsList.postValue(listComments)

}