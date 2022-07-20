package com.imorning.accountbook.ui.disburse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.imorning.accountbook.App
import com.imorning.accountbook.adapter.DisburseAdapter
import com.imorning.accountbook.databinding.FragmentDisburseBinding
import kotlinx.coroutines.launch

class DisburseFragment : Fragment() {

    companion object {
        private const val TAG = "DisburseFragment"
        fun newInstance() = DisburseFragment()
    }

    private var fragmentDisburseBinding: FragmentDisburseBinding? = null

    private val binding get() = fragmentDisburseBinding!!

    private val viewModel: DisburseViewModel by activityViewModels {
        DisburseViewModelFactory(
            (activity?.application as App).database.bookDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDisburseBinding = FragmentDisburseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.disburseRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val disburseAdapter = DisburseAdapter {
            // viewModel.delete(it)
            lifecycle.coroutineScope.launch {
                viewModel.queryAll().collect {

                }
            }
        }
        recyclerView.adapter = disburseAdapter
        lifecycle.coroutineScope.launch {
            viewModel.queryAll().collect {
                disburseAdapter.submitList(it)
            }
        }
    }
}