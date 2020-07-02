package meehan.matthew.petfindr.network

object NetworkConstants {
    private const val PET_API_VERSION = "/v2/"
    const val PET_BASE_ENDPOINT = "https://api.petfinder.com$PET_API_VERSION"

    const val AUTHORIZATION_HEADER = "Authorization"
    const val BEARER = "Bearer "

    const val GRANT_TYPE_KEY = "grant_type"
    const val CLIENT_ID_KEY  = "client_id"
    const val CLIENT_SECRET_KEY = "client_secret"

    const val PAGE = "page"
    const val ID = "id"

    const val GRANT_TYPE_VALUE = "client_credentials"
    const val CLIENT_ID_VALUE  = "lXVpgVyLsX6CWzjLbwL52IOZ8QcKuJPSi1rkAvBIYJ0tFJVXLd"
    const val CLIENT_SECRET_VALUE = "IU3A9miNV424gJJeEnTACbrdD8dEfJB2dq5O1OrW"
}