package com.imorning.accountbook.ui.disburse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.imorning.accountbook.databinding.FragmentDisburseBinding

class DisburseFragment : Fragment() {

    companion object {
        fun newInstance() = DisburseFragment()
    }

    private var fragmentDisburseBinding: FragmentDisburseBinding? = null

    private val binding get() = fragmentDisburseBinding!!

    private lateinit var viewModel: DisburseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[DisburseViewModel::class.java]
        fragmentDisburseBinding = FragmentDisburseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}