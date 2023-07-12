
package com.irmak.themoviedc.adapter
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.WebviewLayoutBinding
import com.irmak.themoviedc.holder.PlayerViewHolder


class VideoAdapter(private val data: List<String>) : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding =
            WebviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val videoUrl = data[position]
        val webView = holder.webviever

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.visibility = View.GONE
        webView.settings.apply {
            javaScriptEnabled = true
            useWideViewPort = false
            loadWithOverviewMode = false
            mediaPlaybackRequiresUserGesture = false

        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webView.visibility = WebView.VISIBLE

            }
        }

        val iframe = """
    <html>
        <head>
            <style type="text/css">
                html, body {
                    margin: 0;
                    padding: 0;
                    overflow: hidden;
                }
                iframe {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                }
            </style>
        </head>
        <body>
            <iframe width="100%" height="100%" src="https://www.youtube.com/embed/$videoUrl?autoplay=1&controls=1&mute=1" frameborder="0" allowfullscreen></iframe>
        </body>
    </html>
""".trimIndent()
        webView.loadDataWithBaseURL(null, iframe, "text/html", "utf-8", null)

    }
    override fun getItemCount(): Int = data.size

}


