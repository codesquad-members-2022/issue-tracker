package com.example.it.issuetracker.presentation.login

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import com.example.it.issuetracker.BuildConfig
import com.example.it.issuetracker.R

private const val GITHUB_OAUTH_URL = "https://github.com/login/oauth/authorize?client_id=${BuildConfig.CLIENT_ID}"

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.setShareState(CustomTabsIntent.SHARE_STATE_ON)
        builder.setUrlBarHidingEnabled(true)

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(GITHUB_OAUTH_URL))
    }
}