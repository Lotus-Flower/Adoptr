package meehan.matthew.petfindr.model.local

import meehan.matthew.petfindr.model.remote.AnimalsItemResponse

data class Pet(

    var pagination: Pagination,

    var animals: List<AnimalsItem>
)

data class AnimalsItem(

    var gender: String,

    var distance: Double,

    var links: Links,

    var description: String,

    var type: String,

    var photos: List<PhotosItem>,

    var url: String,

    var colors: Colors,

    var breeds: Breeds,

    var tags: List<String>,

    var coat: String,

    var environment: Environment,

    var size: String,

    var species: String,

    var organizationId: String,

    var contact: Contact,

    var name: String,

    var attributes: Attributes,

    var id: Int,

    var publishedAt: String,

    var age: String,

    var status: String
)

data class Pagination(

    var links: Links,

    var countPerPage: Int,

    var totalCount: Int,

    var totalPages: Int,

    var currentPage: Int
)

data class Links(

    var organization: Organization,

    var self: Self,

    var type: Type
)

data class Organization(

    var href: String
)

data class Self(

    var href: String
)

data class Type(

    var href: String
)

data class PhotosItem(

    var small: String,

    var large: String,

    var medium: String,

    var full: String
)

data class Colors(

    var secondary: Any,

    var tertiary: Any,

    var primary: String
)

data class Breeds(

    var secondary: Any,

    var mixed: Boolean,

    var primary: String,

    var unknown: Boolean
)

data class Environment(

    var cats: Boolean,

    var children: Boolean,

    var dogs: Boolean
)

data class Contact(

    var address: Address,

    var phone: String,

    var email: String
)

data class Address(

    var country: String,

    var address2: Any,

    var city: String,

    var address1: Any,

    var postcode: String,

    var state: String
)

data class Attributes(

    var shotsCurrent: Boolean,

    var specialNeeds: Boolean,

    var declawed: Boolean,

    var spayedNeutered: Boolean,

    var houseTrained: Boolean
)