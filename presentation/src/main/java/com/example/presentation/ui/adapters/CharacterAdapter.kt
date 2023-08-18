package com.example.presentation.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.domain.models.CharacterModel
import com.example.presentation.databinding.ItemCharacterBinding

class CharacterAdapter(private val deleteCharacter: (characterModel: CharacterModel) -> Unit,
                       private val updateCharacter: (characterModel: CharacterModel) -> Unit) :
    ListAdapter<CharacterModel, CharacterAdapter.ViewHolder>(
        DiffUtilCallback()
    ) {

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(adapterPosition)?.let {
                    deleteCharacter(it)
                    Log.e("dan",it.name)
                }
            }
            binding.updateButton.setOnClickListener {
                getItem(adapterPosition)?.let {
                    updateCharacter(it)
                }
            }
        }

        fun onBind(characterModel: CharacterModel?) {
            Glide.with(binding.itemCharacterImage).load(characterModel?.image)
                .into(binding.itemCharacterImage)
            binding.itemCharacterName.text = characterModel?.name
            binding.itemCharacterSpecies.text = characterModel?.species
            binding.itemCharacterStatus.text = characterModel?.status
            binding.location.text = characterModel?.type

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<CharacterModel>() {

        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }
}