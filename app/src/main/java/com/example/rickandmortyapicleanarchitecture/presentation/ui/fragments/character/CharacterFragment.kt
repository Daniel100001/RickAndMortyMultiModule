package com.example.rickandmortyapicleanarchitecture.presentation.ui.fragments.character

import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapicleanarchitecture.R
import com.example.rickandmortyapicleanarchitecture.databinding.FragmentCharacterBinding
import com.example.rickandmortyapicleanarchitecture.domain.models.CharacterModel
import com.example.rickandmortyapicleanarchitecture.presentation.base.BaseFragment
import com.example.rickandmortyapicleanarchitecture.presentation.state.UIState
import com.example.rickandmortyapicleanarchitecture.presentation.ui.adapters.CharacterAdapter
import com.example.rickandmortyapicleanarchitecture.utils.mapper.CharacterMapperUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewModel>(R.layout.fragment_character) {
    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel by viewModels<CharacterViewModel>()
    private val characterAdapter = CharacterAdapter(this::deleteCharacter,this::updateCharacter)

    private fun updateCharacter(characterModel: CharacterModel) {
        viewModel.updateCharacter(characterModel)

    }

    private fun deleteCharacter(characterModel: CharacterModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.deleteCharacter(characterModel)
        }
        Toast.makeText(requireContext(), "перезайди", Toast.LENGTH_SHORT).show()
    }

    override fun initialize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
    }

    override fun setupListeners() {
        switchListener()
        clearDataBaseListener()
        searchCharacterListener()
    }

    private fun switchListener() {
        binding.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.localState.collect {
                            when (it) {
                                is UIState.Error -> {
                                    Log.e("errorOnline", it.error)
                                }
                                is UIState.Loading -> {
                                    binding.recyclerView.visibility = View.GONE
                                    binding.progressBar.isVisible = true
                                }
                                is UIState.Success -> {
                                    binding.recyclerView.visibility = View.VISIBLE
                                    binding.progressBar.isVisible = false
                                    characterAdapter.submitList(it.data.map { it1 ->
                                        CharacterMapperUI.fromModel(it1)
                                    })
                                }
                            }
                        }
                    }
                }
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.charactersState.collect {
                            when (it) {
                                is UIState.Error -> {
                                    Log.e("errorOffline", it.error)
                                }
                                is UIState.Loading -> {
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
    }

    private fun clearDataBaseListener() {
        binding.button.setOnClickListener{
            viewModel.clear()
        }
    }

    private fun searchCharacterListener() {
        binding.searchView.setOnQueryTextListener((object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    searchCharacter(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchCharacter(newText)
                }
                return true
            }

        }))
    }

    private fun searchCharacter(query: String) {
        val searchQuery = "%$query%"
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchCharacter(searchQuery).collect{list ->
                list.let {
                    characterAdapter.submitList(it)
                }
            }
        }
    }

    override fun setupSubscribes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.charactersState.collect {
                    when (it) {
                        is UIState.Error -> {
                            Log.e("errorD", it.error)
                        }
                        is UIState.Loading -> {
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