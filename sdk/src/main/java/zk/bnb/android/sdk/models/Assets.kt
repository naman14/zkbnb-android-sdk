package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Assets(
    @SerializedName("total") val total: Int,
    @SerializedName("assets") val assets: List<Asset>
)