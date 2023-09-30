package com.browser.webgram.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.browser.webgram.R
import com.browser.webgram.constants.APP_ACTIVITY
import com.browser.webgram.databinding.FragmentHistoryBinding
import com.browser.webgram.database.models.AppNote
import com.browser.webgram.utils.Toast.showMessage
import timber.log.Timber

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var observerList: Observer<List<AppNote>>
    private lateinit var viewModel: HistoryFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[HistoryFragmentViewModel::class.java]
        historyAdapter = HistoryAdapter {
            val bundle = Bundle()
            bundle.putString(key_url, it.text)
            Timber.tag("key_url").d(it.text)
            APP_ACTIVITY.navController.navigate(
                R.id.action_historyFragment_to_browserFragment,
                bundle
            )
        }

        binding.recyclerView.adapter = historyAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        observerList = Observer {
            val list = it
            historyAdapter.submitList(list)
        }
        viewModel.allNotes.observe(viewLifecycleOwner, observerList)

        binding.imageToCloseHistory.setOnClickListener {
            findNavController().navigate(
                R.id.action_historyFragment_to_mainFragment
            )
        }

        binding.textToClearHistory.setOnClickListener {
            viewModel.deleteAll()
            showMessage(requireActivity(), getString(R.string.history_deleted))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.allNotes.removeObserver(observerList)
    }

    companion object {
        private const val key_url = "amount"
    }
}
