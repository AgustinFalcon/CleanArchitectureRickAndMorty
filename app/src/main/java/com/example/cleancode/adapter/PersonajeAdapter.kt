package com.example.cleancode.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleancode.databinding.ItemRvFragmentHomeAdapterBinding
import com.example.cleancode.network.parsedata.personaje.RemotePersonaje


class PersonajeAdapter : RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonajeAdapter.PersonajeViewHolder {
        return PersonajeViewHolder(ItemRvFragmentHomeAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PersonajeAdapter.PersonajeViewHolder, position: Int) {
        val personaje = personajes[position]

        holder.binding.apply {
            tvName.text = personaje.name
            tvGender.text = personaje.gender
            tvSpecie.text = personaje.species
            tvStatus.text = personaje.status
        }
    }

    override fun getItemCount(): Int = personajes.size


    inner class PersonajeViewHolder(val binding: ItemRvFragmentHomeAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<RemotePersonaje>(){
        override fun areItemsTheSame(oldItem: RemotePersonaje, newItem: RemotePersonaje): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RemotePersonaje, newItem: RemotePersonaje): Boolean {
            return oldItem == newItem
        }
    }

    //Take the values of de list from HomeFragment
    private val differ = AsyncListDiffer(this, diffCallBack)
    var personajes: List<RemotePersonaje>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
}