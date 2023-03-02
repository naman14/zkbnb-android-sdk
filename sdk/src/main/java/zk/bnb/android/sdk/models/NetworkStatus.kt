package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class NetworkStatus(
    @SerializedName("status")
    val status: Int,
    @SerializedName("network_id")
    val networkId: Int
)