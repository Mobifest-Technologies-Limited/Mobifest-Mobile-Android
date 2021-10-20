package co.mobifest.mobile.models

import com.google.gson.annotations.SerializedName

data class ProductOrder(
        @SerializedName("productOrderNumber") val productOrderNumber: Int,
        @SerializedName("productOrderStatus") val productOrderStatus: String,
        @SerializedName("productOrderStatusDate") val productOrderStatusDate: String,
        @SerializedName("product") val product: Product
)