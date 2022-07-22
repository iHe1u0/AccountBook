package com.imorning.accountbook.ui.income

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.imorning.accountbook.App
import com.imorning.accountbook.adapter.IncomeAdapter
import com.imorning.accountbook.databinding.FragmentIncomeBinding
import kotlinx.coroutines.launch

private const val TAG = "IncomeFragment"

class IncomeFragment : Fragment() {

    private var fragmentIncomeBinding: FragmentIncomeBinding? = null

    private val binding get() = fragmentIncomeBinding!!

    private val viewModel: IncomeViewModel by activityViewModels {
        IncomeViewModelFactory(
            (activity?.application as App).database.bookDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentIncomeBinding = FragmentIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.incomeRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val incomeAdapter = IncomeAdapter {
            viewModel.delete(it)
            lifecycle.coroutineScope.launch {
                viewModel.queryAll().collect {
                }
            }
//            val action =IncomeFragmentDirections.navIncomeAction()
//            view.findNavController().navigate(action)
        }
        recyclerView.adapter = incomeAdapter
        lifecycle.coroutineScope.launch {
            viewModel.queryAll().collect {
                incomeAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentIncomeBinding = null
    }
}