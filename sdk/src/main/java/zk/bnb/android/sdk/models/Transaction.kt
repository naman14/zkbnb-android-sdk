package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("hash")
    val hash: String,
    @SerializedName("type")
    val type: Long,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("info")
    val info: String,
    @SerializedName("status")
    val status: Long,
    @SerializedName("index")
    val index: Long,
    @SerializedName("gas_fee_asset_id")
    val gasFeeAssetId: Long,
    @SerializedName("gas_fee")
    val gasFee: String,
    @SerializedName("nft_index")
    val nftIndex: Long,
    @SerializedName("asset_id")
    val assetId: Long,
    @SerializedName("asset_name")
    val assetName: String,
    @SerializedName("native_address")
    val nativeAddress: String,
    @SerializedName("extra_info")
    val extraInfo: String,
    @SerializedName("memo")
    val memo: String,
    @SerializedName("account_index")
    val accountIndex: Long,
    @SerializedName("account_name")
    val accountName: String,
    @SerializedName("nonce")
    val nonce: Long,
    @SerializedName("expire_at")
    val expireAt: Long,
    @SerializedName("block_height")
    val blockHeight: Long,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("state_root")
    val stateRoot: String,
)