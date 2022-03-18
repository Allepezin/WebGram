package com.browser.webgram.ui.activities

import android.content.Intent
import android.os.Bundle
import com.browser.webgram.R
import com.browser.webgram.ui.preview.PageAdapter
import com.browser.webgram.databinding.ActivityPreviewBinding
import com.browser.webgram.preferences.AppPreference
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class PreviewActivity : DaggerAppCompatActivity() {

    private lateinit var _binding: ActivityPreviewBinding
    @Inject
    lateinit var appPreference: AppPreference
    var stringVisited: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        isVisited()

        _binding.viewPager2.adapter = PageAdapter(this@PreviewActivity)

        TabLayoutMediator(_binding.tabLayout, _binding.viewPager2) { tab, position ->
        }.attach()

        _binding.buttonPreviewNext.setOnClickListener {
            when (_binding.viewPager2.currentItem) {
                currentItem_0 -> {
                    _binding.viewPager2.currentItem = currentItem_1
                }
                currentItem_1 -> {
                    _binding.viewPager2.currentItem = currentItem_2
                }
                currentItem_2 -> {
                    _binding.viewPager2.currentItem = currentItem_3
                }
                else -> {
                    saveIsFirst()
                }
            }
        }

        _binding.textPreviewSkip.setOnClickListener {
            saveIsFirst()
        }

        _binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    currentItem_0 -> {
                        _binding.buttonPreviewNext.text = getString(R.string.start)
                    }
                    currentItem_3 -> {
                        _binding.buttonPreviewNext.text = getString(R.string.finish)
                    }
                    else -> {
                        _binding.buttonPreviewNext.text = getString(R.string.next)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // to do nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // to do nothing
            }
        })
    }

    private fun isVisited() {
        stringVisited = appPreference.visited
        Timber.tag("visited").d(stringVisited)
        if (stringVisited == "") {
            Timber.tag("visited").d("null")
        } else {
            startActivity(Intent(this@PreviewActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun saveIsFirst() {
        stringVisited = "isVisited"
        appPreference.visited = stringVisited
        startActivity(Intent(this@PreviewActivity, MainActivity::class.java))
        finish()
    }

    companion object {
        const val currentItem_0 = 0
        const val currentItem_1 = 1
        const val currentItem_2 = 2
        const val currentItem_3 = 3
    }
}
