package com.example.lovecalculator.ui.inputs

import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.R
import com.example.lovecalculator.databinding.InputFragmentBinding
import com.example.lovecalculator.util.Constants

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
                    viewModel.result.observe(viewLifecycleOwner){response->
                        sendNotification(response.percentage.toString(),response.result)

                    }

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
    private fun sendNotification(percent:String,resultStr:String){

        val pendingIntent = NavDeepLinkBuilder(requireContext())
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.resultFragment)
            .setArguments(bundleOf("resultStr" to resultStr,"percent" to percent))
            .createPendingIntent()

        val builder = NotificationCompat.Builder(requireContext(), Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Love Calculator")
            .setContentText("Your result is ready : $percent %")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(requireContext())){
            notify(Constants.NOTIFICATION_ID,builder.build())
        }
    }
}