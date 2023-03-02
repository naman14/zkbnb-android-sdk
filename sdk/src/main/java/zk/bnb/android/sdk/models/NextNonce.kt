package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class NextNonce(
    @SerializedName("nonce")
    val nonce: Long
)