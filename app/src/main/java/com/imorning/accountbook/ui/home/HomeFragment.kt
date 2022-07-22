package com.imorning.accountbook.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.imorning.accountbook.App
import com.imorning.accountbook.R
import com.imorning.accountbook.databinding.FragmentHomeBinding
import com.imorning.accountbook.view.OverviewTheme
import com.imorning.accountbook.view.PieChartView

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private var fragmentHomeBinding: FragmentHomeBinding? = null

    private val binding get() = fragmentHomeBinding!!

    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(
            (activity?.application as App).repository
        )
    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        var values = listOf(1.0, 2.0, 3.0, 4.0)
        var categories = resources.getStringArray(R.array.income_type).asList()

//        viewModel.incomeCategories.observe(viewLifecycleOwner, Observer { income_categories ->
//            income_categories.let {
//                categories = it
//            }
//        })

        viewModel.incomeValues.observe(viewLifecycleOwner, Observer { income_values ->
            income_values.let {
                Log.i(TAG, "onCreateView: data changed... $it")
                values = it
            }
        })

        val view = ComposeView(requireContext()).apply {
            setContent {
                OverviewTheme {
                    PieChartView(viewModel)
                }
            }
        }

        return view
//        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
////        val textView: TextView = fragmentHomeBinding.textHome
////        homeViewModel.text.observe(viewLifecycleOwner) {
////            textView.text = it
////        }
//        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        fragmentHomeBinding = null
    }
}