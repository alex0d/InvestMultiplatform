package data.local

import kotlinx.browser.localStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserDataSourceImpl : UserDataSource {

    private val _accessToken = MutableStateFlow<String?>(null)
    private val _refreshToken = MutableStateFlow<String?>(null)
    private val _userFirstname = MutableStateFlow<String?>(null)
    private val _userLastname = MutableStateFlow<String?>(null)
    private val _userEmail = MutableStateFlow<String?>(null)

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

    init {
        _accessToken.value = localStorage.getItem("accessToken")
        _refreshToken.value = localStorage.getItem("refreshToken")
        _userFirstname.value = localStorage.getItem("userFirstname")
        _userLastname.value = localStorage.getItem("userLastname")
        _userEmail.value = localStorage.getItem("userEmail")
    }

    override suspend fun saveAccessToken(token: String) {
        localStorage.setItem("accessToken", token)
        _accessToken.value = token
    }

    override suspend fun saveRefreshToken(token: String) {
        localStorage.setItem("refreshToken", token)
        _refreshToken.value = token
    }

    override suspend fun saveUserFirstname(firstname: String) {
        localStorage.setItem("userFirstname", firstname)
        _userFirstname.value = firstname
    }

    override suspend fun saveUserLastname(lastname: String?) {
        localStorage.setItem("userLastname", lastname ?: "")
        _userLastname.value = lastname
    }

    override suspend fun saveUserEmail(email: String) {
        localStorage.setItem("userEmail", email)
        _userEmail.value = email
    }

    override suspend fun clear() {
        localStorage.clear()
        _accessToken.value = null
        _refreshToken.value = null
        _userFirstname.value = null
        _userLastname.value = null
        _userEmail.value = null
    }

    override val accessToken: Flow<String?> = _accessToken.asStateFlow()

    override val refreshToken: Flow<String?> = _refreshToken.asStateFlow()

    override val userFirstname: Flow<String?> = _userFirstname.asStateFlow()

    override val userLastname: Flow<String?> = _userLastname.asStateFlow()

    override val userEmail: Flow<String?> = _userEmail.asStateFlow()
}