package com.browser.webgram.ui.preview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return count_4
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            count_0 -> {
                return Preview1Fragment()
            }
            count_1 -> {
                return Preview2Fragment()
            }
            count_2 -> {
                return Preview3Fragment()
            }
            count_3 -> {
                return Preview4Fragment()
            }
            else -> {
                return Preview1Fragment()
            }
        }
    }

    companion object {
        const val count_0 = 0
        const val count_1 = 1
        const val count_2 = 2
        const val count_3 = 3
        const val count_4 = 4
    }
}
