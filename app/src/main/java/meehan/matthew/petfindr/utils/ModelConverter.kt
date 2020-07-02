package meehan.matthew.petfindr.utils

interface ModelConverter <T, S> {
    fun convertFromResponse(from: S): T
}