package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Accounts(
    @SerializedName("total") val total: Int,
    @SerializedName("accounts") val accounts: List<SimpleAccount>
)