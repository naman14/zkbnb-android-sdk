package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class MaxOfferId(
    @SerializedName("offer_id")
    val offerId: Long
)