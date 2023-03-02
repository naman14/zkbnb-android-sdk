package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("data_type")
    val dataType: Int
)

enum class DataType(val type: Int) {
    ACCOUNT(2),
    PK(4),
    BLOCK(9),
    TX(10)
}