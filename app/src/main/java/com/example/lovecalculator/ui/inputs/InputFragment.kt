package com.example.lovecalculator.ui.inputs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.R
import com.example.lovecalculator.databinding.InputFragmentBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class InputFragment : Fragment(R.layout.input_fragment) {
    private val viewModel: InputViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = InputFragmentBinding.bind(view)

        binding.apply {
            submit.setOnClickListener {
                if (yourName.text.toString().equals("") ){
                    yourName.setError("Enter both the name")
                }else if (  partnerName.text.toString().equals("")){
                    partnerName.setError("Enter name")
                }
                else {
                    viewModel.getResults(yourName.text.toString(), partnerName.text.toString())
                }
            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.event.collect { event ->
                    when (event) {
                        is InputViewModel.MyEvent.NavigateToResultFragment -> {

                            val action  = InputFragmentDirections.actionInputFragmentToResultFragment()
                            findNavController().navigate(action)


                        }
                    }
                }

            }
        }


    }
}