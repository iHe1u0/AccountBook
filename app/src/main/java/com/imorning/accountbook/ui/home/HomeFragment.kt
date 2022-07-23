package com.imorning.accountbook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.imorning.accountbook.App
import com.imorning.accountbook.R
import com.imorning.accountbook.databinding.FragmentHomeBinding
import com.imorning.accountbook.view.ContentScreen
import com.imorning.accountbook.view.OverviewTheme

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private var fragmentHomeBinding: FragmentHomeBinding? = null

    private val binding get() = fragmentHomeBinding!!

    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(
            (activity?.application as App).repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        var values:List<Double> = listOf(1.0, 2.0, 3.0, 4.0)
        var categories = resources.getStringArray(R.array.income_type).asList()

        viewModel.incomeValues.observe(viewLifecycleOwner) { income_values ->
            income_values.let {
                values = it
            }
        }

        val view = inflater.inflate(R.layout.fragment_home, container, false).apply {
            findViewById<ComposeView>(R.id.home_compose_view).setContent {
                OverviewTheme {
                    ContentScreen(viewModel)
                }
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentHomeBinding = null
    }
}