package co.mobifest.mobile.models

import com.google.gson.annotations.SerializedName

data class Product (
        @SerializedName("categoryId") val productId: Int,
        @SerializedName("categoryName") val productName: String
)