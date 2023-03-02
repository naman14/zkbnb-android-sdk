package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Block(
    @SerializedName("commitment")
    val commitment: String,
    @SerializedName("height")
    val height: Long,
    @SerializedName("state_root")
    val stateRoot: String,
    @SerializedName("priority_operations")
    val priorityOperations: Long,
    @SerializedName("pending_on_chain_operations_hash")
    val pendingOnChainOperationsHash: String,
    @SerializedName("pending_on_chain_operations_pub_data")
    val pendingOnChainOperationsPubData: String,
    @SerializedName("committed_tx_hash")
    val committedTxHash: String,
    @SerializedName("committed_at")
    val committedAt: Long,
    @SerializedName("verified_tx_hash")
    val verifiedTxHash: String,
    @SerializedName("verified_at")
    val verifiedAt: Long,
    @SerializedName("txs")
    val txs: List<Transaction>,
    @SerializedName("status")
    val status: Long,
    @SerializedName("size")
    val size: Long
)