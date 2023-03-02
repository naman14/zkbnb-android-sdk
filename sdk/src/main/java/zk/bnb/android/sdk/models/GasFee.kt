package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class GasFee(
    @SerializedName("gas_fee")
    val gasFee: String
)