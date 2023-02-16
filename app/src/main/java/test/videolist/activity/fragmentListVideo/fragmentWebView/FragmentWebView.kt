package test.videolist.activity.fragmentListVideo.fragmentWebView

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.core.content.PackageManagerCompat.LOG_TAG
import architecture.BaseFragment
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener

import test.videolist.databinding.FragmentWebViewBinding

class FragmentWebView : BaseFragment<FragmentWebViewBinding>(FragmentWebViewBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAppsFlyer(APP_KEY)
        showWebView(LINK)
    }

    private fun initAppsFlyer(key: String) {
        AppsFlyerLib.getInstance().init(key, null, requireActivity())
        AppsFlyerLib.getInstance().start(requireActivity())
        AppsFlyerLib.getInstance().setDebugLog(true)
        /*setRequestListener(key)*/
    }

    private fun setRequestListener(key: String) {
        AppsFlyerLib.getInstance().start(requireActivity(), key, object :
            AppsFlyerRequestListener {
            @SuppressLint("RestrictedApi")
            override fun onSuccess() {
                Log.d(LOG_TAG, "Launch sent successfully")
            }

            @SuppressLint("RestrictedApi")
            override fun onError(errorCode: Int, errorDesc: String) {
                Log.d(
                    LOG_TAG, "Launch failed to be sent:\n" +
                            "Error code: " + errorCode + "\n"
                            + "Error description: " + errorDesc
                )
            }
        })
    }

    private fun showWebView(url: String) {
        with(binding) {
            wvMain.webViewClient = WebViewClient()
            wvMain.settings.javaScriptEnabled = true
            wvMain.loadUrl(url)
        }
    }

    companion object {
        const val APP_KEY = "VDTfibQz4cB2kTDuxqX2cG"
        const val LINK = "https://app.appsflyer.com/test.videolist?pid=devtest&c=test1"
    }
}