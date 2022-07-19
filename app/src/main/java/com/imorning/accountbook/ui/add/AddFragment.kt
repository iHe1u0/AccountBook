package com.imorning.accountbook.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.imorning.accountbook.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private var addFragmentBinding: FragmentAddBinding? = null
    private lateinit var viewModel: AddViewModel
    private val binding get() = addFragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // viewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        addFragmentBinding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}