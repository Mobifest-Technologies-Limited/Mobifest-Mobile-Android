package co.mobifest.mobile.models

import com.google.gson.annotations.SerializedName

data class Category (
        @SerializedName("categoryId") val catId: Int,
        @SerializedName("categoryName") val catName: String
        )