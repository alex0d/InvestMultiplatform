package data.local

import kotlinx.coroutines.flow.Flow

class UserDataSourceImpl(
    private val userDataStore: UserDataStore
) : UserDataSource {
    override suspend fun saveUserDetails(
        accessToken: String,
        refreshToken: String,
        firstname: String,
        lastname: String?,
        email: String
    ) = userDataStore.saveUserDetails(accessToken, refreshToken, firstname, lastname, email)

    override suspend fun saveAccessToken(token: String) = userDataStore.saveAccessToken(token)

    override suspend fun saveRefreshToken(token: String) = userDataStore.saveRefreshToken(token)

    override suspend fun saveUserFirstname(firstname: String) = userDataStore.saveUserFirstname(firstname)

    override suspend fun saveUserLastname(lastname: String?) = userDataStore.saveUserLastname(lastname)

    override suspend fun saveUserEmail(email: String) = userDataStore.saveUserEmail(email)

    override suspend fun clear() = userDataStore.clear()

    override val accessToken: Flow<String?>
        get() = userDataStore.accessToken

    override val refreshToken: Flow<String?>
        get() = userDataStore.refreshToken

    override val userFirstname: Flow<String?>
        get() = userDataStore.userFirstname

    override val userLastname: Flow<String?>
        get() = userDataStore.userLastname

    override val userEmail: Flow<String?>
        get() = userDataStore.userEmail

}