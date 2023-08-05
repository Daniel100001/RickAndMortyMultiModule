package com.example.rickandmortyapicleanarchitecture.presentation.ui.fragments.character.detail

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.rickandmortyapicleanarchitecture.R
import com.example.rickandmortyapicleanarchitecture.base.BaseFragment
import com.example.rickandmortyapicleanarchitecture.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyapicleanarchitecture.domain.either.Either
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(R.layout.fragment_character_detail) {
    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    override val viewModel by viewModels<CharacterDetailViewModel>()
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun initialize() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchSingleCharacter(args.id).collect {
                when (it) {
                    is Either.Left -> {
                        if (it.message != null) {
                            Log.e("error", it.message)
                        } else {
                            Toast.makeText(requireContext(), "null", Toast.LENGTH_SHORT).show()
                        }

                    }
                    is Either.Right -> {
                        if (it.data?.name != null) {
                            binding.name.text = it.data.name
                        } else {
                            binding.name.text = "null"
                        }

                        Glide.with(binding.imageView).load(it.data?.image)
                            .into(binding.imageView)
                    }
                }
            }
        }
    }
}
