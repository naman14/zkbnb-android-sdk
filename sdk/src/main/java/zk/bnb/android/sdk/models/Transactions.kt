package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Transactions(
    @SerializedName("total") val total: Int,
    @SerializedName("txs") val transactions: List<Transaction>
)