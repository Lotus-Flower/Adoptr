package meehan.matthew.petfindr.utils

import androidx.core.text.isDigitsOnly
import meehan.matthew.petfindr.model.remote.AnimalsItemResponse

object StringHelper {

    fun getPetQuickDescription(pet: AnimalsItemResponse) : String {
        var description = ""

        pet.name?.let { name ->
            if (name.isNotEmpty()) {
                description += name

                if (name.split(" ").last().isDigitsOnly()) {
                    description = description.dropLast(5)
                }

                pet.breeds?.let { breeds ->
                    if (!breeds.primary.isNullOrEmpty()) {
                        description += ", " + breeds.primary

                        breeds.mixed?.let {
                            if (it) {
                                description += " Mix"
                            }
                        }
                    }
                }
            }
        }

        return description
    }

}