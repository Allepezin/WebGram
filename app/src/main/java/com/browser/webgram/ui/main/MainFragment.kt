package com.browser.webgram.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.browser.webgram.R
import com.browser.webgram.databinding.FragmentMainBinding
import com.browser.webgram.preferences.AppPreference
import com.browser.webgram.api.ApiService
import com.browser.webgram.api.Ip
import com.browser.webgram.utils.Keyboard.afterTextChangedFlow
import com.browser.webgram.utils.Toast.showMessage
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainFragmentViewModel
    @Inject
    lateinit var appPreference: AppPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.initDatabase()
        getIpAddress()
        binding.switchToAdblock.isChecked = appPreference.checked == 2
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        binding.imageToHistory.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_historyFragment)
        }

        binding.editTextToSearchBrowser.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchOrLoad()
                true
            } else {
                searchOrLoad()
                false
            }
        }

        binding.switchToAdblock.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                appPreference.checked = 2
            } else if (!isChecked) {
                appPreference.checked = 1
            }
        }

        lifecycleScope.launch {
            binding.editTextToSearchBrowser.afterTextChangedFlow().collect {
                binding.textToSearch.text = it
            }
        }
    }

    private fun searchOrLoad() {
        if (binding.editTextToSearchBrowser.text.toString() == "") {
            showMessage(requireActivity(), getString(R.string.enter_something))
        } else {
            val bundle = Bundle()
            bundle.putString(key_url, binding.editTextToSearchBrowser.text.toString())
            bundle.putInt(key_adblock, appPreference.checked)
            Timber.tag("key_adblock").d(appPreference.checked.toString())
            findNavController().navigate(
                R.id.action_mainFragment_to_browserFragment,
                bundle
            )
            binding.editTextToSearchBrowser.text.clear()
        }
    }

    private fun getIpAddress() {
        val apiService = ApiService.create().getIp()

        apiService.enqueue(object : Callback<Ip> {
            override fun onResponse(call: Call<Ip>?, response: Response<Ip>?) {
                if (response?.body() != null) {
                    val myIp = response.body()!!.ip.toString()
                    binding.textIp.text = myIp
                }
            }

            override fun onFailure(call: Call<Ip>?, t: Throwable?) {
                binding.textIp.text = getString(R.string.error)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val key_url = "amount"
        const val key_adblock = "adblock"
    }
}
