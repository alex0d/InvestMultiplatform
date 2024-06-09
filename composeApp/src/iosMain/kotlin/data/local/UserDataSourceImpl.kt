package data.local

import kotlinx.coroutines.flow.Flow

class UserDataSourceImpl: UserDataSource {
    override suspend fun saveUserDetails(
        accessToken: String,
        refreshToken: String,
        firstname: String,
        lastname: String?,
        email: String
    ) = TODO("Not yet implemented")

    override suspend fun saveAccessToken(token: String) = TODO("Not yet implemented")

    override suspend fun saveRefreshToken(token: String) = TODO("Not yet implemented")

    override suspend fun saveUserFirstname(firstname: String) = TODO("Not yet implemented")

    override suspend fun saveUserLastname(lastname: String?) = TODO("Not yet implemented")

    override suspend fun saveUserEmail(email: String) = TODO("Not yet implemented")

    override suspend fun clear() = TODO("Not yet implemented")

    override val accessToken: Flow<String?>
        get() = TODO("Not yet implemented")

    override val refreshToken: Flow<String?>
        get() = TODO("Not yet implemented")

    override val userFirstname: Flow<String?>
        get() = TODO("Not yet implemented")

    override val userLastname: Flow<String?>
        get() = TODO("Not yet implemented")

    override val userEmail: Flow<String?>
        get() = TODO("Not yet implemented")

}