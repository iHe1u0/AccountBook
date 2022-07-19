package com.imorning.accountbook.ui.income

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.imorning.accountbook.App
import com.imorning.accountbook.adapter.AccountBookAdapter
import com.imorning.accountbook.databinding.FragmentIncomeBinding
import com.imorning.accountbook.viewmodels.DatabaseViewModel
import com.imorning.accountbook.viewmodels.DatabaseViewModelFactory
import kotlinx.coroutines.launch

class IncomeFragment : Fragment() {

    companion object {
        private const val TAG = "IncomeFragment"
        fun newInstance() = IncomeFragment()
    }

    private var fragmentIncomeBinding: FragmentIncomeBinding? = null

    private val binding get() = fragmentIncomeBinding!!

    private val databaseViewModel: DatabaseViewModel by activityViewModels {
        DatabaseViewModelFactory(
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
        val accountBookAdapter = AccountBookAdapter {
            Log.i(TAG, "id is ${it.id}")
            // databaseViewModel.delete(it)
            lifecycle.coroutineScope.launch {
                databaseViewModel.queryIncome().collect { it ->
                    Log.i(TAG, "all data: $it")
                }
            }
//            val action =IncomeFragmentDirections.navIncomeAction()
//            view.findNavController().navigate(action)
        }
        recyclerView.adapter = accountBookAdapter
        lifecycle.coroutineScope.launch {
            databaseViewModel.getAll().collect {
                accountBookAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentIncomeBinding = null
    }
}