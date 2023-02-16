package test.videolist.activity.fragmentListVideo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.videolist.model.Comment
import test.videolist.model.HardcodeVideoData
import test.videolist.model.VideoItem

class FragmentListVideoViewModel : ViewModel() {

    private val _listVideoItems = MutableLiveData<List<VideoItem>>()
    val listVideoItems: LiveData<List<VideoItem>> = _listVideoItems

    init {
        _listVideoItems.value = HardcodeVideoData.getVideo()
    }

    fun searchVideoWithName(name: String) {
        _listVideoItems.value = HardcodeVideoData.getVideo().filter {
            it.name.contains(name)
        }
    }

    fun filterListByCategory(name: String) {
        _listVideoItems.value = HardcodeVideoData.getVideo().filter {
            it.category.contains(name)
        }
    }

    fun changeFavoriteStatusOfVideo(video: VideoItem) =
        _listVideoItems.value?.contains(video.apply { inUserFavorite = !inUserFavorite })

    fun filterListByMyFavorite() {
        _listVideoItems.value = _listVideoItems.value?.filter {
            it.inUserFavorite == true
        }
    }

    fun changeVideoRate(video: VideoItem, newRating: Float) =
        _listVideoItems.value?.contains(video.apply { rating = newRating })

    fun saveComments(video: VideoItem, listComments: List<Comment>) =
        _listVideoItems.value?.contains(video.apply { commentsList = listComments })

}