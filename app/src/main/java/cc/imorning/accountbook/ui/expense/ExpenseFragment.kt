package cc.imorning.accountbook.ui.expense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import cc.imorning.accountbook.App
import cc.imorning.accountbook.adapter.DisburseAdapter
import cc.imorning.accountbook.databinding.FragmentExpenseBinding
import kotlinx.coroutines.launch

class ExpenseFragment : Fragment() {

    companion object {
        private const val TAG = "ExpenseFragment"
        fun newInstance() = ExpenseFragment()
    }

    private var expenseBinding: FragmentExpenseBinding? = null

    private val binding get() = expenseBinding!!

    private val viewModel: ExpenseViewModel by activityViewModels {
        ExpenseViewModelFactory(
            (activity?.application as cc.imorning.accountbook.App).database.bookDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        expenseBinding = FragmentExpenseBinding.inflate(inflater, container, false)
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