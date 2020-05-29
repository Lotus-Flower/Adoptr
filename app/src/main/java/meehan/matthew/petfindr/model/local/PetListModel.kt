package meehan.matthew.petfindr.model.local

import android.os.Parcel
import android.os.Parcelable
import meehan.matthew.petfindr.model.remote.*
import meehan.matthew.petfindr.utils.StringHelper

data class PetListModel(

    var next: String,

    var petList: List<PetModel>

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.createTypedArrayList(PetModel.CREATOR).orEmpty()
    )

    companion object {

        @JvmField val CREATOR : Parcelable.Creator<PetListModel> = object : Parcelable.Creator<PetListModel> {
            override fun createFromParcel(parcel: Parcel): PetListModel {
                return PetListModel(parcel)
            }

            override fun newArray(size: Int): Array<PetListModel?> {
                return arrayOfNulls(size)
            }
        }

        fun convertFromResponse(petResponse: PetResponse?) : PetListModel {
            return PetListModel(
                next = petResponse?.pagination?.links?.next?.href ?: "",
                petList = petResponse?.animals?.map {
                    PetModel.convertFromResponse(it)
                } ?: listOf()
            )
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(next)
        parcel.writeTypedList(petList)
    }

    override fun describeContents(): Int {
        return 0
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

    var attributes: AttributesModel,

    var id: String,

    var publishedAt: String,

    var age: String,

    var quickDescription: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(LinkModel::class.java.classLoader) ?: LinkModel("", ""),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(EnvironmentModel::class.java.classLoader) ?: EnvironmentModel("", "", ""),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(ContactModel::class.java.classLoader) ?: ContactModel("", "", ""),
        parcel.readString().toString(),
        parcel.readParcelable(AttributesModel::class.java.classLoader) ?: AttributesModel("", "", "", "", ""),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    companion object {

        @JvmField val CREATOR : Parcelable.Creator<PetModel> = object : Parcelable.Creator<PetModel> {
            override fun createFromParcel(parcel: Parcel): PetModel {
                return PetModel(parcel)
            }

            override fun newArray(size: Int): Array<PetModel?> {
                return arrayOfNulls(size)
            }
        }

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
                attributes = AttributesModel.convertFromResponse(animalsItemResponse?.attributes),
                id = animalsItemResponse?.id.toString(),
                publishedAt = animalsItemResponse?.publishedAt ?: "",
                age = StringHelper.checkAge(animalsItemResponse?.age),
                quickDescription = StringHelper.getPetQuickDescription(animalsItemResponse)
            )
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(gender)
        parcel.writeString(distance)
        parcel.writeParcelable(links, flags)
        parcel.writeString(description)
        parcel.writeString(type)
        parcel.writeString(photoUrl)
        parcel.writeString(url)
        parcel.writeString(breeds)
        parcel.writeParcelable(environment, flags)
        parcel.writeString(size)
        parcel.writeString(species)
        parcel.writeParcelable(contact, flags)
        parcel.writeString(name)
        parcel.writeParcelable(attributes, flags)
        parcel.writeString(id)
        parcel.writeString(publishedAt)
        parcel.writeString(age)
        parcel.writeString(quickDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

}

data class LinkModel(

    var self: String,

    var organization: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    companion object {

        @JvmField val CREATOR : Parcelable.Creator<LinkModel> = object : Parcelable.Creator<LinkModel> {
            override fun createFromParcel(parcel: Parcel): LinkModel {
                return LinkModel(parcel)
            }

            override fun newArray(size: Int): Array<LinkModel?> {
                return arrayOfNulls(size)
            }
        }

        fun convertFromResponse(linksResponse: LinksResponse?) : LinkModel {
            return LinkModel(
                self = linksResponse?.self?.href ?: "",
                organization = linksResponse?.organization?.href ?: ""
            )
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(self)
        parcel.writeString(organization)
    }

    override fun describeContents(): Int {
        return 0
    }

}

data class EnvironmentModel(

    var children: String,

    var dogs: String,

    var cats: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    companion object {

        @JvmField val CREATOR : Parcelable.Creator<EnvironmentModel> = object : Parcelable.Creator<EnvironmentModel> {
            override fun createFromParcel(parcel: Parcel): EnvironmentModel {
                return EnvironmentModel(parcel)
            }

            override fun newArray(size: Int): Array<EnvironmentModel?> {
                return arrayOfNulls(size)
            }
        }

        fun convertFromResponse(environmentResponse: EnvironmentResponse?) : EnvironmentModel {
            return StringHelper.convertEnvironmentStrings(environmentResponse)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(children)
        parcel.writeString(dogs)
        parcel.writeString(cats)
    }

    override fun describeContents(): Int {
        return 0
    }

}

data class ContactModel(

    var address: String,

    var phone: String,

    var email: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    companion object {
        @JvmField val CREATOR : Parcelable.Creator<ContactModel> = object : Parcelable.Creator<ContactModel> {
            override fun createFromParcel(parcel: Parcel): ContactModel {
                return ContactModel(parcel)
            }

            override fun newArray(size: Int): Array<ContactModel?> {
                return arrayOfNulls(size)
            }
        }

        fun convertFromResponse(contactResponse: ContactResponse?) : ContactModel {
            return StringHelper.convertContactStrings(contactResponse)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

}

data class AttributesModel(

    var spayedNeutered: String,

    var houseTrained: String,

    var declawed: String,

    var specialNeeds: String,

    var shotsCurrent: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(spayedNeutered)
        parcel.writeString(houseTrained)
        parcel.writeString(declawed)
        parcel.writeString(specialNeeds)
        parcel.writeString(shotsCurrent)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField val CREATOR : Parcelable.Creator<AttributesModel> = object : Parcelable.Creator<AttributesModel> {
            override fun createFromParcel(parcel: Parcel): AttributesModel {
                return AttributesModel(parcel)
            }

            override fun newArray(size: Int): Array<AttributesModel?> {
                return arrayOfNulls(size)
            }
        }

        fun convertFromResponse(attributesResponse: AttributesResponse?) : AttributesModel {
            return StringHelper.convertAttributeStrings(attributesResponse)
        }
    }

}