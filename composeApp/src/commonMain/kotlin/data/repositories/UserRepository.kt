package data.repositories

import data.local.UserDataSource
import data.remote.models.AuthRequest
import data.remote.models.RegisterRequest
import data.remote.services.AuthApiService
import domain.models.AuthResult
import kotlinx.coroutines.flow.first

class UserRepository(
    private val apiService: AuthApiService,
    private val userDataSource: UserDataSource
) {

    suspend fun register(firstname: String, lastname: String?, email: String, password: String): AuthResult {
        val request = RegisterRequest(
            firstname = firstname,
            lastname = lastname,
            email = email,
            password = password
        )

        val response = try {
            apiService.register(request)
        } catch (e: Exception) {
            return AuthResult.UNKNOWN_ERROR
        }

//        return if (response.isSuccessful && response.body() != null) {
//            userDataSource.saveUserDetails(
//                accessToken = response.body()!!.accessToken,
//                refreshToken = response.body()!!.refreshToken,
//                firstname = response.body()!!.firstname,
//                lastname = response.body()!!.lastname,
//                email = response.body()!!.email
//            )
//            AuthResult.SUCCESS
//        } else if (response.code() == 409) {
//            AuthResult.EMAIL_ALREADY_REGISTERED
//        } else {
//            AuthResult.UNKNOWN_ERROR
//        }

        userDataSource.saveUserDetails(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
            firstname = response.firstname,
            lastname = response.lastname,
            email = response.email
        )

        return AuthResult.SUCCESS
    }

    suspend fun authenticate(email: String, password: String): AuthResult {
        val request = AuthRequest(
            email = email,
            password = password
        )

        val response = try {
            apiService.authenticate(request)
        } catch (e: Exception) {
            return AuthResult.UNKNOWN_ERROR
        }

//        return if (response.isSuccessful && response.body() != null) {
//            userDataSource.saveUserDetails(
//                accessToken = response.body()!!.accessToken,
//                refreshToken = response.body()!!.refreshToken,
//                firstname = response.body()!!.firstname,
//                lastname = response.body()!!.lastname,
//                email = response.body()!!.email
//            )
//            AuthResult.SUCCESS
//        } else if (response.code() == 401) {
//            AuthResult.INVALID_CREDENTIALS
//        } else if (response.code() == 422) {
//            AuthResult.USER_NOT_FOUND
//        } else {
//            AuthResult.UNKNOWN_ERROR
//        }

        userDataSource.saveUserDetails(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
            firstname = response.firstname,
            lastname = response.lastname,
            email = response.email
        )

        return AuthResult.SUCCESS
    }

    suspend fun authenticateByTokensInDataBase(): AuthResult {
        val accessToken = userDataSource.accessToken.first()
        val refreshToken = userDataSource.refreshToken.first()

        return if (accessToken != null && refreshToken != null) {
            AuthResult.SUCCESS
        } else {
            AuthResult.INVALID_CREDENTIALS
        }
    }

    suspend fun logout() {
        userDataSource.clear()
    }

    suspend fun getUserFirstname(): String? {
        return userDataSource.userFirstname.first()
    }

    suspend fun getUserLastname(): String? {
        return userDataSource.userLastname.first()
    }

    suspend fun getUserEmail(): String? {
        return userDataSource.userEmail.first()
    }
}