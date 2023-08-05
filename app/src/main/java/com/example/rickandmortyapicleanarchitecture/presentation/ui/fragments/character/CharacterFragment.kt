package com.example.rickandmortyapicleanarchitecture.presentation.ui.fragments.character

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapicleanarchitecture.R
import com.example.rickandmortyapicleanarchitecture.base.BaseFragment
import com.example.rickandmortyapicleanarchitecture.databinding.FragmentCharacterBinding
import com.example.rickandmortyapicleanarchitecture.presentation.state.UIState
import com.example.rickandmortyapicleanarchitecture.presentation.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding, CharacterViewModel>(R.layout.fragment_character) {
    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel by viewModels<CharacterViewModel>()
    private val characterAdapter = CharacterAdapter(this::onItemClick)

    private fun onItemClick(id : Int) {
        findNavController().navigate(CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(id))
    }

    override fun initialize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
    }

    override fun setupSubscribes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.charactersState.collect {
                    when (it){
                        is UIState.Error ->{
                            Log.e("errorD", it.error)
                        }
                        is UIState.Loading ->{
                         binding.recyclerView.visibility = View.GONE
                         binding.progressBar.isVisible = true
                        }

                        is UIState.Success -> {
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.isVisible = false
                            characterAdapter.submitList(it.data)
                        }
                    }
                }
            }
        }
    }
}