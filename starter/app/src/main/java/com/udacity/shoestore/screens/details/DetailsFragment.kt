package com.udacity.shoestore.screens.details

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.screens.list.ListViewModel

/**
 * Fragment where the game is played
 */
class DetailsFragment : Fragment() {

    val shoes: ListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false)

        // Inflate view and obtain an instance of the binding class
        binding.shoes = shoes

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        shoes.returnToList.observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().navigateUp()
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        shoes.resetReturnToList()
    }
}