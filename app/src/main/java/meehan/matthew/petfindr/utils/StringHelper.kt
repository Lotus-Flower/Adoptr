package meehan.matthew.petfindr.utils

import androidx.core.text.isDigitsOnly
import meehan.matthew.petfindr.model.remote.AddressResponse
import meehan.matthew.petfindr.model.remote.AnimalsItemResponse
import meehan.matthew.petfindr.model.remote.BreedsResponse

object StringHelper {

    fun getPetQuickDescription(pet: AnimalsItemResponse?) : String {
        var description = ""

        pet?.name?.let { name ->
            if (name.isNotEmpty()) {
                description += name

                if (name.split(" ").last().isDigitsOnly()) {
                    description = description.dropLast(5)
                }

                if (getBreed(pet.breeds).isNotEmpty()){
                    description += (", " + getBreed(pet.breeds))
                }
            }
        }

        return description
    }

    fun getBreed(breedsResponse: BreedsResponse?) : String {

        var breedString = ""

        breedsResponse?.let { breeds ->
            if (!breeds.primary.isNullOrEmpty()) {
                breedString += breeds.primary

                breeds.mixed?.let {
                    if (it) {
                        breedString += " Mix"
                    }
                }
            }
        }

        return breedString
    }

    fun createAddress(addressResponse: AddressResponse?) : String {
        return addressResponse?.address1.toString() + ", " +
                addressResponse?.city + ", " +
                addressResponse?.state + ", " +
                addressResponse?.postcode + ", " +
                addressResponse?.country
    }

    fun checkUnknown(boolean: Boolean?) : String {
        return when (boolean) {
            true -> "True"
            false -> "False"
            else -> "Unknown"
        }
    }

    fun checkUnknown(string: String?) : String {
        return when (string.isNullOrEmpty()) {
            false -> string.toString()
            else -> "Unknown"
        }
    }

    fun checkUnknownNumber(string: String?) : String {
        return when (string?.isDigitsOnly()) {
            true -> string.toString()
            else -> "Unknown"
        }
    }

}