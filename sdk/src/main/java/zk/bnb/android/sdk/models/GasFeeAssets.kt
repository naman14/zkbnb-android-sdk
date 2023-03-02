package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class GasFeeAssets(
    @SerializedName("assets")
    val assets: List<Asset>
)