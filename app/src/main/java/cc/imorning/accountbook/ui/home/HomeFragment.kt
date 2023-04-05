package cc.imorning.accountbook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cc.imorning.accountbook.App
import cc.imorning.accountbook.R
import cc.imorning.accountbook.databinding.FragmentHomeBinding
import cc.imorning.accountbook.view.ContentScreen
import cc.imorning.accountbook.view.OverviewTheme

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private var fragmentHomeBinding: FragmentHomeBinding? = null

    private val binding get() = fragmentHomeBinding!!

    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(
            (activity?.application as cc.imorning.accountbook.App).repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        // viewModel.incomeLists.observe(viewLifecycleOwner) {
        // }

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