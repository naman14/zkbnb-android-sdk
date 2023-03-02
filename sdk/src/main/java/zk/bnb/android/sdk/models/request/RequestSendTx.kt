package zk.bnb.android.sdk.models.request

import com.google.gson.annotations.SerializedName

data class RequestSendTx(
    @SerializedName("tx_type") val txType: Int,
    @SerializedName("tx_info") val txInfo: String,
)