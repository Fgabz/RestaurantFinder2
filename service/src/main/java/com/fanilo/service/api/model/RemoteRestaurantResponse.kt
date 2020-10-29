package com.fanilo.service.api.model

import com.google.gson.annotations.SerializedName

data class RemoteRestaurantResponse(
    @SerializedName("response")
    val response: Response
)

data class Response(
    @SerializedName("venues")
    val venues: List<Venue>?
)

data class Venue(
    @SerializedName("categories")
    val categories: List<Category?>?,
    @SerializedName("delivery")
    val delivery: Delivery?,
    @SerializedName("hasPerk")
    val hasPerk: Boolean?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("referralId")
    val referralId: String?,
    @SerializedName("venuePage")
    val venuePage: VenuePage?
)

data class Category(
    @SerializedName("icon")
    val icon: Icon?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("pluralName")
    val pluralName: String?,
    @SerializedName("primary")
    val primary: Boolean?,
    @SerializedName("shortName")
    val shortName: String?
) {
    data class Icon(
        @SerializedName("prefix")
        val prefix: String?,
        @SerializedName("suffix")
        val suffix: String?
    )
}

data class Delivery(
    @SerializedName("id")
    val id: String?,
    @SerializedName("provider")
    val provider: Provider?,
    @SerializedName("url")
    val url: String?
) {
    data class Provider(
        @SerializedName("icon")
        val icon: Icon?,
        @SerializedName("name")
        val name: String?
    ) {
        data class Icon(
            @SerializedName("name")
            val name: String?,
            @SerializedName("prefix")
            val prefix: String?,
            @SerializedName("sizes")
            val sizes: List<Int?>?
        )
    }
}

data class Location(
    @SerializedName("address")
    val address: String?,
    @SerializedName("cc")
    val cc: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("crossStreet")
    val crossStreet: String?,
    @SerializedName("distance")
    val distance: Int?,
    @SerializedName("formattedAddress")
    val formattedAddress: List<String?>?,
    @SerializedName("labeledLatLngs")
    val labeledLatLngs: List<LabeledLatLng?>?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lng")
    val lng: Double?,
    @SerializedName("neighborhood")
    val neighborhood: String?,
    @SerializedName("postalCode")
    val postalCode: String?,
    @SerializedName("state")
    val state: String?
) {
    data class LabeledLatLng(
        @SerializedName("label")
        val label: String?,
        @SerializedName("lat")
        val lat: Double?,
        @SerializedName("lng")
        val lng: Double?
    )
}

data class VenuePage(
    @SerializedName("id")
    val id: String?
)