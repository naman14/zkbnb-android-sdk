package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Nfts(
    @SerializedName("total") val total: Int,
    @SerializedName("nfts") val nfts: List<Nft>
)