package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("status")
    val status: Int
)