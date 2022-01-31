package co.mobifest.mobile.models

import com.google.gson.annotations.SerializedName

data class Rental(
    @SerializedName("id") val rentalId: Int,
    @SerializedName("rentalType") val rentalType: String,
    @SerializedName("location") val location: String,
    @SerializedName("status") val status: String,
    @SerializedName("isAvailable") val isAvailable: Boolean,
    @SerializedName("isApproved") val isApproved: Boolean,
    @SerializedName("price") val price: Float,
    @SerializedName("currency") val currency: String,
    @SerializedName(" surface") val surface: String,
    @SerializedName("description") val description: String?,
    @SerializedName("size") val size: String,
    @SerializedName("code") val code: String,
    @SerializedName("district") val district: String,
    @SerializedName("isInspectable") val isInspectable: Boolean,
    @SerializedName("bedRoomCount") val bedRoomCount: Int?,
    @SerializedName("bathRoomCount") val bathRoomCount: Int?,
    @SerializedName("parkingCount") val parkingCount: Int?
)