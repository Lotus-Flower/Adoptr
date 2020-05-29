package meehan.matthew.petfindr.utils

import androidx.core.text.isDigitsOnly
import meehan.matthew.petfindr.model.local.AttributesModel
import meehan.matthew.petfindr.model.local.ContactModel
import meehan.matthew.petfindr.model.local.EnvironmentModel
import meehan.matthew.petfindr.model.remote.*

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

    private fun createAddress(addressResponse: AddressResponse?) : String {
        var addressString = ""

        addressResponse?.address1?.let {
            addressString += "$it, "
        }

        addressResponse?.city?.let {
            addressString += "$it, "
        }

        addressResponse?.state?.let {
            addressString += "$it, "
        }

        addressResponse?.postcode?.let {
            addressString += "$it, "
        }

        addressResponse?.country?.let {
            addressString += it
        }

        return when (addressString.isEmpty()) {
            true -> "No address listed"
            else -> addressString
        }
    }

    fun convertEnvironmentStrings(environmentResponse: EnvironmentResponse?) : EnvironmentModel {
        val envModel = EnvironmentModel("", "", "")

        envModel.children = when (environmentResponse?.children) {
            true -> "Children tolerant"
            false -> "Not children tolerant"
            else -> "Unknown children tolerance"
        }

        envModel.dogs = when (environmentResponse?.dogs) {
            true -> "Dog tolerant"
            false -> "Not dog tolerant"
            else -> "Unknown dog tolerance"
        }

        envModel.cats = when (environmentResponse?.cats) {
            true -> "Cat tolerant"
            false -> "Not cat tolerant"
            else -> "Unknown cat tolerance"
        }

        return envModel
    }

    fun convertAttributeStrings(attributesResponse: AttributesResponse?) : AttributesModel {
        val attributeModel = AttributesModel("", "", "", "", "")

        attributeModel.spayedNeutered = when (attributesResponse?.spayedNeutered) {
            true -> "Spayed/Neutered"
            false -> "Not spayed/neutered"
            else -> "Unknown if spayed/neutered"
        }

        attributeModel.houseTrained = when (attributesResponse?.houseTrained) {
            true -> "House trained"
            false -> "Not house trained"
            else -> "Unknown if house trained"
        }

        attributeModel.declawed = when (attributesResponse?.declawed) {
            true -> "Declawed"
            false -> "Not declawed"
            else -> "Unknown if declawed"
        }

        attributeModel.specialNeeds = when (attributesResponse?.specialNeeds) {
            true -> "Special needs"
            false -> "No special needs"
            else -> "Unknown if special needs"
        }

        attributeModel.shotsCurrent = when (attributesResponse?.shotsCurrent) {
            true -> "Vaccinations up-to-date"
            false -> "Vaccinations not up-to-date"
            else -> "Unknown if vaccinations are up-to-date"
        }

        return attributeModel
    }

    fun convertContactStrings(contactResponse: ContactResponse?) : ContactModel {
        val contactModel = ContactModel("", "", "")

        contactModel.address = createAddress(contactResponse?.address)

        if (contactResponse?.phone == null) {
            contactModel.phone = "No phone number listed"
        } else {
            contactModel.phone = contactResponse.phone
        }

        if (contactResponse?.email == null) {
            contactModel.email = "No email listed"
        } else {
            contactModel.email = contactResponse.email
        }

        return contactModel
    }

    fun checkAge(string: String?) : String {
        return when (string?.isDigitsOnly()) {
            true -> string.toString()
            else -> "Unknown Age"
        }
    }

}