package com.example.lovecalculator.ui.results

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.lovecalculator.R
import com.example.lovecalculator.databinding.ResultFragmentBinding
import com.example.lovecalculator.ui.inputs.InputViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.result_fragment) {

    val viewModel : InputViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ResultFragmentBinding.bind(view)
        viewModel.result.observe(viewLifecycleOwner){response->
            binding.apply {
                resultText.text = response.result
                resultPer.text = response.percentage.toString()
            }

        }




    }
}