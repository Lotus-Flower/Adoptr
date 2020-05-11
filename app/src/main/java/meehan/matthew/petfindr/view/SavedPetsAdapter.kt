package meehan.matthew.petfindr.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meehan.matthew.petfindr.R
import meehan.matthew.petfindr.model.local.Pet

class SavedPetsAdapter(val savedPetsList: ArrayList<Pet>) : RecyclerView.Adapter<SavedPetsAdapter.SavedPetsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedPetsViewHolder {
        return SavedPetsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_saved_pets, parent, false))
    }

    override fun getItemCount(): Int = savedPetsList.size

    override fun onBindViewHolder(holder: SavedPetsViewHolder, position: Int) {

    }

    class SavedPetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}