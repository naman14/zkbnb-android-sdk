package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Layer2BasicInfo(
    @SerializedName("block_committed")
    val blockCommitted: Long,
    @SerializedName("block_verified")
    val blockVerified: Long,
    @SerializedName("total_transaction_count")
    val totalTransactionCount: Long,
    @SerializedName("yesterday_transaction_count")
    val yesterdayTransactionCount: Long,
    @SerializedName("today_transaction_count")
    val todayTransactionCount: Long,
    @SerializedName("yesterday_active_user_count")
    val yesterdayActiveUserCount: Long,
    @SerializedName("today_active_user_count")
    val todayActiveUserCount: Long,
    @SerializedName("contract_addresses")
    val contractAddresses: List<ContractAddress>
)