package com.fanilo.service.api.model

import com.google.gson.annotations.SerializedName

data class RemoteRestaurantResponse(
    @SerializedName("response")
    val response: Response
)

data class Response(
    @SerializedName("venues")
    val venues: List<RemoteVenue>
)

data class RemoteVenue(
    @SerializedName("categories")
    val categories: List<RemoteCategory>,
    @SerializedName("delivery")
    val delivery: RemoteDelivery?,
    @SerializedName("hasPerk")
    val hasPerk: Boolean?,
    @SerializedName("id")
    val id: String,
    @SerializedName("location")
    val location: RemoteLocation,
    @SerializedName("name")
    val name: String,
    @SerializedName("referralId")
    val referralId: String?,
    @SerializedName("venuePage")
    val venuePage: RemoteVenuePage?
)

data class RemoteCategory(
    @SerializedName("icon")
    val icon: Icon?,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("pluralName")
    val pluralName: String,
    @SerializedName("primary")
    val primary: Boolean,
    @SerializedName("shortName")
    val shortName: String
) {
    data class Icon(
        @SerializedName("prefix")
        val prefix: String?,
        @SerializedName("suffix")
        val suffix: String?
    )
}

data class RemoteDelivery(
    @SerializedName("id")
    val id: String?,
    @SerializedName("provider")
    val provider: RemoteProvider?,
    @SerializedName("url")
    val url: String?
) {
    data class RemoteProvider(
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

data class RemoteLocation(
    @SerializedName("address")
    val address: String?,
    @SerializedName("cc")
    val cc: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("crossStreet")
    val crossStreet: String,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("formattedAddress")
    val formattedAddress: List<String>,
    @SerializedName("labeledLatLngs")
    val labeledLatLngs: List<RemoteLabeledLatLng>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("state")
    val state: String
) {
    data class RemoteLabeledLatLng(
        @SerializedName("label")
        val label: String?,
        @SerializedName("lat")
        val lat: Double?,
        @SerializedName("lng")
        val lng: Double?
    )
}

data class RemoteVenuePage(
    @SerializedName("id")
    val id: String?
)