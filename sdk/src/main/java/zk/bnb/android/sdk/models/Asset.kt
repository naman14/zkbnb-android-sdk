package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Asset(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("decimals")
    val decimals: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("is_gas_asset")
    val isGasAsset: Int,
    @SerializedName("icon")
    val icon: String,
)