package zk.bnb.android.sdk.models

import com.google.gson.annotations.SerializedName

data class Nft(
    @SerializedName("index")
    val index: Long,
    @SerializedName("creator_account_index")
    val creatorAccountIndex: Long,
    @SerializedName("owner_account_index")
    val ownerAccountIndex: Long,
    @SerializedName("content_hash")
    val contentHash: String,
    @SerializedName("l1_address")
    val l1Address: String,
    @SerializedName("l1_token_id")
    val l1TokenId: String,
    @SerializedName("creator_treasury_rate")
    val creatorTreasuryRate: Long,
    @SerializedName("collection_id")
    val collectionId: Long
)