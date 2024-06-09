package domain.models

enum class AuthResult {
    SUCCESS,
    USER_NOT_FOUND,
    EMAIL_ALREADY_REGISTERED,
    INVALID_CREDENTIALS,
    UNKNOWN_ERROR
}