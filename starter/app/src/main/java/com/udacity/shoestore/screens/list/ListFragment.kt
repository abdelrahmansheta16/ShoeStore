package com.udacity.shoestore.screens.list

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.*
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.list_details.view.*

/**
 * Fragment where the game is played
 */
class ListFragment : Fragment() {

    private val viewModel by activityViewModels<ListViewModel>()

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )

        // Set up LiveData observation relationship
        viewModel.shoes.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach { shoe ->
                val view: View = inflater.inflate(R.layout.list_details, null, false)
                view.name.text = shoe.name
                view.size.text = shoe.size.toString()
                view.company.text = shoe.company
                view.description.text = shoe.description

                binding.shoeList.addView(view)
            }
        })

        setHasOptionsMenu(true)

        // Floating action button listener
        binding.newShoeButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailsFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        logOut()
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun logOut() {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToLoginFragment())
    }
}