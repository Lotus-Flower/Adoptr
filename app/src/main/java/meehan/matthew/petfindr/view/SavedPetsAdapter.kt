package meehan.matthew.petfindr.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import meehan.matthew.petfindr.databinding.ViewHolderSavedPetsBinding
import meehan.matthew.petfindr.model.local.PetModel

class SavedPetsAdapter(private var savedPetsList: MutableList<PetModel?>, private val clickListener: (PetModel?, ImageView) -> Unit, private val deletePetListener: (PetModel?) -> Unit) : RecyclerView.Adapter<SavedPetsAdapter.SavedPetsViewHolder>() {

    fun update(savedPetsList: MutableList<PetModel?>) {
        this.savedPetsList.clear()
        this.savedPetsList.addAll(savedPetsList)
        this.savedPetsList.sortBy { it?.name }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedPetsViewHolder {
        return SavedPetsViewHolder(ViewHolderSavedPetsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = savedPetsList.size

    override fun onBindViewHolder(holder: SavedPetsViewHolder, position: Int){
        holder.bind(savedPetsList[position])
    }

    inner class SavedPetsViewHolder(private val binding: ViewHolderSavedPetsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (item: PetModel?) {
            binding.petModel = item
            binding.savedPetVhImage.transitionName = binding.petModel?.photoUrl
            itemView.setOnClickListener {
                clickListener.invoke(item, binding.savedPetVhImage)
            }
            binding.deletePetButton.setOnClickListener {
                deletePetListener.invoke(item)
            }
        }
    }
}