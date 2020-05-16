package meehan.matthew.petfindr.model.remote

import com.google.gson.annotations.SerializedName
import meehan.matthew.petfindr.model.local.Links
import com.google.gson.annotations.Expose



data class PetResponse(

	@field:SerializedName("pagination")
	val pagination: PaginationResponse? = null,

	@field:SerializedName("animals")
	val animals: List<AnimalsItemResponse?>? = null
)

data class SingleAnimalResponse(

	@field:SerializedName("animal")
	val animal: AnimalsItemResponse? = null

)

data class AnimalsItemResponse(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("distance")
	val distance: Double? = null,

	@field:SerializedName("_links")
	val links: LinksResponse? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("photos")
	val photos: List<PhotosItemResponse?>? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("colors")
	val colors: ColorsResponse? = null,

	@field:SerializedName("breeds")
	val breeds: BreedsResponse? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null,

	@field:SerializedName("coat")
	val coat: String? = null,

	@field:SerializedName("environment")
	val environment: EnvironmentResponse? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("species")
	val species: String? = null,

	@field:SerializedName("organization_id")
	val organizationId: String? = null,

	@field:SerializedName("contact")
	val contact: ContactResponse? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("attributes")
	val attributes: AttributesResponse? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("published_at")
	val publishedAt: String? = null,

	@field:SerializedName("age")
	val age: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class PaginationResponse(

	@field:SerializedName("_links")
	val links: PaginationLinks? = null,

	@field:SerializedName("count_per_page")
	val countPerPage: Int? = null,

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)

data class LinksResponse(

	@field:SerializedName("organization")
	val organization: SingleLinkResponse? = null,

	@field:SerializedName("self")
	val self: SingleLinkResponse? = null,

	@field:SerializedName("type")
	val type: SingleLinkResponse? = null
)

data class SingleLinkResponse(

	@field:SerializedName("href")
	val href: String? = null
)

data class PhotosItemResponse(

	@field:SerializedName("small")
	val small: String? = null,

	@field:SerializedName("large")
	val large: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null,

	@field:SerializedName("full")
	val full: String? = null
)

data class ColorsResponse(

	@field:SerializedName("secondary")
	val secondary: Any? = null,

	@field:SerializedName("tertiary")
	val tertiary: Any? = null,

	@field:SerializedName("primary")
	val primary: String? = null
)

data class BreedsResponse(

	@field:SerializedName("secondary")
	val secondary: Any? = null,

	@field:SerializedName("mixed")
	val mixed: Boolean? = null,

	@field:SerializedName("primary")
	val primary: String? = null,

	@field:SerializedName("unknown")
	val unknown: Boolean? = null
)

data class EnvironmentResponse(

	@field:SerializedName("cats")
	val cats: Boolean? = null,

	@field:SerializedName("children")
	val children: Boolean? = null,

	@field:SerializedName("dogs")
	val dogs: Boolean? = null
)

data class ContactResponse(

	@field:SerializedName("address")
	val address: AddressResponse? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class AddressResponse(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("address2")
	val address2: Any? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("address1")
	val address1: Any? = null,

	@field:SerializedName("postcode")
	val postcode: String? = null,

	@field:SerializedName("state")
	val state: String? = null
)

data class AttributesResponse(

	@field:SerializedName("shots_current")
	val shotsCurrent: Boolean? = null,

	@field:SerializedName("special_needs")
	val specialNeeds: Boolean? = null,

	@field:SerializedName("declawed")
	val declawed: Boolean? = null,

	@field:SerializedName("spayed_neutered")
	val spayedNeutered: Boolean? = null,

	@field:SerializedName("house_trained")
	val houseTrained: Boolean? = null
)

data class PaginationLinks(

	@SerializedName("next")
	@Expose
	var next: SingleLinkResponse? = null

)