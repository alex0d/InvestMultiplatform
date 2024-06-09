package data.local

import kotlinx.browser.localStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class UserDataSourceImpl : UserDataSource {
    private val accessTokenFlow = MutableSharedFlow<String?>(replay = 1)
    private val refreshTokenFlow = MutableSharedFlow<String?>(replay = 1)
    private val userFirstnameFlow = MutableSharedFlow<String?>(replay = 1)
    private val userLastnameFlow = MutableSharedFlow<String?>(replay = 1)
    private val userEmailFlow = MutableSharedFlow<String?>(replay = 1)

    override suspend fun saveUserDetails(
        accessToken: String,
        refreshToken: String,
        firstname: String,
        lastname: String?,
        email: String
    ) {
        saveAccessToken(accessToken)
        saveRefreshToken(refreshToken)
        saveUserFirstname(firstname)
        saveUserLastname(lastname)
        saveUserEmail(email)
    }

    override suspend fun saveAccessToken(token: String) {
        localStorage.setItem("accessToken", token)
        accessTokenFlow.emit(token)
    }

    override suspend fun saveRefreshToken(token: String) {
        localStorage.setItem("refreshToken", token)
        refreshTokenFlow.emit(token)
    }

    override suspend fun saveUserFirstname(firstname: String) {
        localStorage.setItem("userFirstname", firstname)
        userFirstnameFlow.emit(firstname)
    }

    override suspend fun saveUserLastname(lastname: String?) {
        localStorage.setItem("userLastname", lastname ?: "")
        userLastnameFlow.emit(lastname)
    }

    override suspend fun saveUserEmail(email: String) {
        localStorage.setItem("userEmail", email)
        userEmailFlow.emit(email)
    }

    override suspend fun clear() {
        localStorage.clear()
        accessTokenFlow.emit(null)
        refreshTokenFlow.emit(null)
        userFirstnameFlow.emit(null)
        userLastnameFlow.emit(null)
        userEmailFlow.emit(null)
    }

    override val accessToken: Flow<String?> = accessTokenFlow.asSharedFlow()
    override val refreshToken: Flow<String?> = refreshTokenFlow.asSharedFlow()
    override val userFirstname: Flow<String?> = userFirstnameFlow.asSharedFlow()
    override val userLastname: Flow<String?> = userLastnameFlow.asSharedFlow()
    override val userEmail: Flow<String?> = userEmailFlow.asSharedFlow()
}