package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class SimpleAccount(
    @SerializedName("index")
    val index: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("pk")
    val pk: String
)