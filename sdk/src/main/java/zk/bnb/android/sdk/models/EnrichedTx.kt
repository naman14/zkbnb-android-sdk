package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class EnrichedTx(
    @SerializedName("committed_at")
    val committedAt: Long,
    @SerializedName("verified_at")
    val verifiedAt: Long,
    @SerializedName("executed_at")
    val executedAt: Long
)