package data.remote.services

import data.remote.models.AuthRequest
import data.remote.models.AuthResponse
import data.remote.models.RefreshRequest
import data.remote.models.RegisterRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthApiService(
    private val httpClient: HttpClient,
    private val investApiBaseUrl: String
) {
    suspend fun authenticate(request: AuthRequest): AuthResponse {
        return httpClient.post("$investApiBaseUrl/api/auth/authenticate") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun register(request: RegisterRequest): AuthResponse {
        return httpClient.post("$investApiBaseUrl/api/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun refresh(request: RefreshRequest): AuthResponse {
        return httpClient.post("$investApiBaseUrl/api/auth/refresh") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}