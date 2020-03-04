package meehan.matthew.petfindr.utils

interface ModelConverter <T, S> {
    fun convert(from: S): T
}