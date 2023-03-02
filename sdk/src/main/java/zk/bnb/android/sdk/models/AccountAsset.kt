package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class AccountAsset(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("price")
    val price: String
)