package br.com.souzabrunoj.challenge_catho.ui.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import br.com.souzabrunoj.challenge_catho.R
import br.com.souzabrunoj.challenge_catho.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_web_view.*

class WebViewFragment : BaseFragment() {

    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView()
    }

    private fun setupWebView(){
        wv_container.loadUrl(args.url)
        wv_container.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }
    }
}