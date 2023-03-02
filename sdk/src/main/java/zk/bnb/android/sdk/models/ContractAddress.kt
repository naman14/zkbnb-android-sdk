package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class ContractAddress(
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
)