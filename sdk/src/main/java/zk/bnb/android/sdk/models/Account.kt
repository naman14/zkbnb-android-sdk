package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("status")
    val status: Int,
    @SerializedName("index")
    val index: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("pk")
    val pk: String,
    @SerializedName("nonce")
    val nonce: Long,
    @SerializedName("assets")
    val assets: List<AccountAsset>,
    @SerializedName("total_asset_value")
    val totalAssetValue: String
)