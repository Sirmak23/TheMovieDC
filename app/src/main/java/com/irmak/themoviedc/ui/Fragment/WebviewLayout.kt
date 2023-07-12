package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irmak.themoviedc.databinding.WebviewLayoutBinding

class WebviewLayout : Fragment() {
    lateinit var binding: WebviewLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WebviewLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        webviewListen()
//    }
//    private fun webviewListen() {
//        binding.webviewTrail.webViewClient = object : WebViewClient() {
//            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                super.onPageStarted(view, url, favicon)
//            }
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                view?.evaluateJavascript(
//                    "(function() { " +
//                            "var video = document.querySelector('video');" +
//                            "video.addEventListener('ended', function() { " +
//                            "   window.android.onVideoEnded();" +
//                            "});" +
//                            "})()"
//                ) {
//                }
//            }
//        }
//    }
//    @JavascriptInterface
//    fun onVideoEnded() {
//        isAutoScrollEnabledS = true
////        Handler(Looper.getMainLooper()).postDelayed({
////            isAutoScrollEnabledS = false
////
////        }, 2000)
//    }
}