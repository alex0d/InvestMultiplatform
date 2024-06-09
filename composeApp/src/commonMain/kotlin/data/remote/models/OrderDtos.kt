package data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class BuyStockRequest(
    var uid: String,
    var amount: Int
)

@Serializable
data class SellStockRequest(
    var uid: String,
    var amount: Int
)