package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class GasAccount(
    @SerializedName("status")
    val status: Long,
    @SerializedName("index")
    val index: Long,
    @SerializedName("name")
    val name: String
)