package co.mobifest.mobile.models

import com.google.gson.annotations.SerializedName

data class ProductCategory (
        @SerializedName("categoryId") val catId: Int,
        @SerializedName("categoryName") val catName: String
        )