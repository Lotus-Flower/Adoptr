package meehan.matthew.petfindr.model.local

import meehan.matthew.petfindr.model.remote.*
import meehan.matthew.petfindr.utils.StringHelper

data class PetListModel(

    var next: String,

    var petList: List<PetModel>

) {
    companion object {
        fun convertFromResponse(petResponse: PetResponse?) : PetListModel {
            return PetListModel(
                next = petResponse?.pagination?.links?.next?.href ?: "",
                petList = petResponse?.animals?.map {
                    PetModel.convertFromResponse(it)
                } ?: listOf()
            )
        }
    }
}

data class SingleAnimalModel(

    var pet: PetModel

) {
    companion object {
        fun convertFromResponse(singleAnimalResponse: SingleAnimalResponse?) : SingleAnimalModel {
            return SingleAnimalModel(
                PetModel.convertFromResponse(singleAnimalResponse?.animal)
            )
        }
    }
}

//TODO Add attributes, they are important
data class PetModel(

    var gender: String,

    var distance: String,

    var links: LinkModel,

    var description: String,

    var type: String,

    var photoUrl: String,

    var url: String,

    var breeds: String,

    var environment: EnvironmentModel,

    var size: String,

    var species: String,

    var contact: ContactModel,

    var name: String,

    var id: String,

    var publishedAt: String,

    var age: String,

    var quickDescription: String

) {
    companion object {
        fun convertFromResponse(animalsItemResponse: AnimalsItemResponse?) : PetModel {
            return PetModel(
                gender = animalsItemResponse?.gender ?: "",
                distance = animalsItemResponse?.distance.toString(),
                links = LinkModel.convertFromResponse(animalsItemResponse?.links),
                description = animalsItemResponse?.description ?: "",
                type = animalsItemResponse?.type ?: "",
                photoUrl = animalsItemResponse?.photos?.firstOrNull()?.medium ?: "",
                url = animalsItemResponse?.url ?: "",
                breeds = StringHelper.getBreed(animalsItemResponse?.breeds),
                environment = EnvironmentModel.convertFromResponse(animalsItemResponse?.environment),
                size = animalsItemResponse?.size ?: "",
                species = animalsItemResponse?.species ?: "",
                contact = ContactModel.convertFromResponse(animalsItemResponse?.contact),
                name = animalsItemResponse?.name ?: "",
                id = animalsItemResponse?.id.toString(),
                publishedAt = animalsItemResponse?.publishedAt ?: "",
                age = StringHelper.checkUnknownNumber(animalsItemResponse?.age),
                quickDescription = StringHelper.getPetQuickDescription(animalsItemResponse)
            )
        }
    }
}

data class LinkModel(

    var self: String,

    var organization: String

) {
    companion object {
        fun convertFromResponse(linksResponse: LinksResponse?) : LinkModel {
            return LinkModel(
                self = linksResponse?.self?.href ?: "",
                organization = linksResponse?.organization?.href ?: ""
            )
        }
    }
}

data class EnvironmentModel(

    var children: String,

    var dogs: String,

    var cats: String

) {
    companion object {
        fun convertFromResponse(environmentResponse: EnvironmentResponse?) : EnvironmentModel {
            return EnvironmentModel(
                children = StringHelper.checkUnknown(environmentResponse?.children),
                dogs = StringHelper.checkUnknown(environmentResponse?.dogs),
                cats = StringHelper.checkUnknown(environmentResponse?.cats)
            )
        }
    }
}

data class ContactModel(

    var address: String,

    var phone: String,

    var email: String

) {
    companion object {
        fun convertFromResponse(contactResponse: ContactResponse?) : ContactModel {
            return ContactModel(
                address = StringHelper.createAddress(contactResponse?.address),
                phone = StringHelper.checkUnknown(contactResponse?.phone),
                email = StringHelper.checkUnknown(contactResponse?.email)
            )
        }
    }
}