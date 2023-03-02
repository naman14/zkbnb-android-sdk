package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Blocks(
    @SerializedName("total") val total: Int,
    @SerializedName("blocks") val blocks: List<Block>
)