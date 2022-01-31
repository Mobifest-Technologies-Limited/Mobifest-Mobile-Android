package co.mobifest.mobile.models

import com.google.gson.annotations.SerializedName

data class Product(
        @SerializedName("categoryId") val productId: Int,
        @SerializedName("categoryName") val productName: String,
        @SerializedName("shipment") val shipment: String,
        @SerializedName("productPrice") val productPrice: Double,
        @SerializedName("productSize") val productSize: String?
)