package meehan.matthew.petfindr.model.local

import android.os.Parcel
import android.os.Parcelable
import meehan.matthew.petfindr.model.remote.*
import meehan.matthew.petfindr.utils.ModelConverter
import meehan.matthew.petfindr.utils.StringHelper

data class PetListModel(

    var next: String,

    var petList: List<PetModel>

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.createTypedArrayList(PetModel.CREATOR).orEmpty()
    )

    companion object : ModelConverter<PetListModel, PetResponse?> {

        @JvmField val CREATOR : Parcelable.Creator<PetListModel> = object : Parcelable.Creator<PetListModel> {
            override fun createFromParcel(parcel: Parcel): PetListModel {
                return PetListModel(parcel)
            }

            override fun newArray(size: Int): Array<PetListModel?> {
                return arrayOfNulls(size)
            }
        }

        override fun convertFromResponse(from: PetResponse?) : PetListModel {
            return PetListModel(
                next = from?.pagination?.links?.next?.href ?: "",
                petList = from?.animals?.map {
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
    companion object : ModelConverter<SingleAnimalModel, SingleAnimalResponse?> {
        override fun convertFromResponse(from: SingleAnimalResponse?) : SingleAnimalModel {
            return SingleAnimalModel(
                PetModel.convertFromResponse(from?.animal)
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

    companion object : ModelConverter<PetModel, AnimalsItemResponse?> {

        @JvmField val CREATOR : Parcelable.Creator<PetModel> = object : Parcelable.Creator<PetModel> {
            override fun createFromParcel(parcel: Parcel): PetModel {
                return PetModel(parcel)
            }

            override fun newArray(size: Int): Array<PetModel?> {
                return arrayOfNulls(size)
            }
        }

        override fun convertFromResponse(from: AnimalsItemResponse?) : PetModel {
            return PetModel(
                gender = from?.gender ?: "",
                distance = from?.distance.toString(),
                links = LinkModel.convertFromResponse(from?.links),
                description = from?.description ?: "",
                type = from?.type ?: "",
                photoUrl = from?.photos?.firstOrNull()?.medium ?: "",
                url = from?.url ?: "",
                breeds = StringHelper.getBreed(from?.breeds),
                environment = EnvironmentModel.convertFromResponse(from?.environment),
                size = from?.size ?: "",
                species = from?.species ?: "",
                contact = ContactModel.convertFromResponse(from?.contact),
                name = from?.name ?: "",
                attributes = AttributesModel.convertFromResponse(from?.attributes),
                id = from?.id.toString(),
                publishedAt = from?.publishedAt ?: "",
                age = StringHelper.checkAge(from?.age),
                quickDescription = StringHelper.getPetQuickDescription(from)
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

    companion object : ModelConverter<LinkModel, LinksResponse?> {

        @JvmField val CREATOR : Parcelable.Creator<LinkModel> = object : Parcelable.Creator<LinkModel> {
            override fun createFromParcel(parcel: Parcel): LinkModel {
                return LinkModel(parcel)
            }

            override fun newArray(size: Int): Array<LinkModel?> {
                return arrayOfNulls(size)
            }
        }

        override fun convertFromResponse(from: LinksResponse?) : LinkModel {
            return LinkModel(
                self = from?.self?.href ?: "",
                organization = from?.organization?.href ?: ""
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

    companion object : ModelConverter<EnvironmentModel, EnvironmentResponse?> {

        @JvmField val CREATOR : Parcelable.Creator<EnvironmentModel> = object : Parcelable.Creator<EnvironmentModel> {
            override fun createFromParcel(parcel: Parcel): EnvironmentModel {
                return EnvironmentModel(parcel)
            }

            override fun newArray(size: Int): Array<EnvironmentModel?> {
                return arrayOfNulls(size)
            }
        }

        override fun convertFromResponse(from: EnvironmentResponse?) : EnvironmentModel {
            return StringHelper.convertEnvironmentStrings(from)
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

    companion object : ModelConverter<ContactModel, ContactResponse?> {
        @JvmField val CREATOR : Parcelable.Creator<ContactModel> = object : Parcelable.Creator<ContactModel> {
            override fun createFromParcel(parcel: Parcel): ContactModel {
                return ContactModel(parcel)
            }

            override fun newArray(size: Int): Array<ContactModel?> {
                return arrayOfNulls(size)
            }
        }

        override fun convertFromResponse(from: ContactResponse?) : ContactModel {
            return StringHelper.convertContactStrings(from)
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

    companion object : ModelConverter<AttributesModel, AttributesResponse? >{
        @JvmField val CREATOR : Parcelable.Creator<AttributesModel> = object : Parcelable.Creator<AttributesModel> {
            override fun createFromParcel(parcel: Parcel): AttributesModel {
                return AttributesModel(parcel)
            }

            override fun newArray(size: Int): Array<AttributesModel?> {
                return arrayOfNulls(size)
            }
        }

        override fun convertFromResponse(from: AttributesResponse?) : AttributesModel {
            return StringHelper.convertAttributeStrings(from)
        }
    }

}