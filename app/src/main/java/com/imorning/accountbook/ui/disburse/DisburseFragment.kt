package com.imorning.accountbook.ui.disburse

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imorning.accountbook.R

class DisburseFragment : Fragment() {

    companion object {
        fun newInstance() = DisburseFragment()
    }

    private lateinit var viewModel: DisburseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_disburse, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DisburseViewModel::class.java)
    }

}