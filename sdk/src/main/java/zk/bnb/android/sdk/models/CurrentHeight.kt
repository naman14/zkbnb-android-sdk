package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class CurrentHeight(
    @SerializedName("height")
    val height: Long,
)