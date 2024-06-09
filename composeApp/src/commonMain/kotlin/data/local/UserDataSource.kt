package data.local

import kotlinx.coroutines.flow.Flow

interface UserDataSource {

    suspend fun saveUserDetails(accessToken: String, refreshToken: String, firstname: String, lastname: String?, email: String)

    suspend fun saveAccessToken(token: String)

    suspend fun saveRefreshToken(token: String)

    suspend fun saveUserFirstname(firstname: String)

    suspend fun saveUserLastname(lastname: String?)

    suspend fun saveUserEmail(email: String)

    suspend fun clear()

    val accessToken: Flow<String?>

    val refreshToken: Flow<String?>

    val userFirstname: Flow<String?>

    val userLastname: Flow<String?>

    val userEmail: Flow<String?>
}