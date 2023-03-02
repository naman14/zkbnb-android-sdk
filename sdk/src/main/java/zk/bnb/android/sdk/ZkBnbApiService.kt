package zk.bnb.android.sdk

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import zk.bnb.android.sdk.models.Account
import zk.bnb.android.sdk.models.NetworkStatus
import zk.bnb.android.sdk.models.request.RequestSendTx

interface ZkBnbApiService {

    /*
    Get status of zkbnb
     */
    @GET(".")
    suspend fun status(): NetworkStatus

    /*
    Get account by account's name, index or pk
     */
    @GET("api/v1/account")
    suspend fun getAccount(@Query("by") by: String, @Query("value") value: String): Account

    /*
    Get pending transactions of a specific account
     */
    @GET("api/v1/accountPendingTxs")
    suspend fun getAccountPendingTxs(
        @Query("by") by: String,
        @Query("value") value: String
    ): Account

    /*
   Get nfts of a specific account
    */
    @GET("api/v1/accountNfts")
    suspend fun getAccountNfts(
        @Query("by") by: String,
        @Query("value") value: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Account

    /*
    Get accounts
    */
    @GET("api/v1/accounts")
    suspend fun getAccounts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Account

    /*
    Get asset
    */
    @GET("api/v1/asset")
    suspend fun getAsset(
        @Query("by") by: String,
        @Query("value") value: String
    ): Account

    /*
    Get assets
    */
    @GET("api/v1/assets")
    suspend fun getAssets(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Account

    /*
    Get block by its height or commitment
    */
    @GET("api/v1/block")
    suspend fun getBlock(
        @Query("by") by: String,
        @Query("value") value: String
    ): Account

    /*
    Get transactions in a block
    */
    @GET("api/v1/blockTxs")
    suspend fun getBlockTxs(
        @Query("by") by: String,
        @Query("value") value: String
    ): Account

    /*
    Get blocks
    */
    @GET("api/v1/blocks")
    suspend fun getBlocks(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Account

    /*
    Get current height
    */
    @GET("api/v1/currentHeight")
    suspend fun getCurrentHeight(): Account

    /*
    Get gas account, who will charge gas fees for transactions
    */
    @GET("api/v1/gasAccount")
    suspend fun getGasAccount(): Account

    /*
    Get gas fee amount for using a specific asset as gas asset
    */
    @GET("api/v1/gasFee")
    suspend fun getGasFee(
        @Query("asset_id") assetId: Int,
        @Query("tx_type") txType: Int
    ): Account

    /*
    Get gas fee amount for using a specific asset as gas asset
    */
    @GET("api/v1/gasFeeAssets")
    suspend fun getGasFeeAssets(): Account

    /*
    Get zkbnb general info, including contract address, and count of transactions and active users
    */
    @GET("api/v1/layer2BasicInfo")
    suspend fun getLayer2BasicInfo(): Account

    /*
    Get zkbnb general info, including contract address, and count of transactions and active users
    */
    @GET("api/v1/maxOfferId")
    suspend fun getMaxOfferId(@Query("account_index") accountIndex: Int): Account

    /*
    Get pending transactions
    */
    @GET("api/v1/pendingTxs")
    suspend fun getPendingTransactions(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Account

    /*
    Get executed transactions
    */
    @GET("api/v1/executedTxs")
    suspend fun getExecutedTransactions(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("from_hash") fromHash: String?
    ): Account

    /*
    Get next nonce
    */
    @GET("api/v1/nextNonce")
    suspend fun getNextNonce(
        @Query("account_index") accountIndex: Int
    ): Account

    /*
    Search with a specific keyword
    */
    @GET("api/v1/search")
    suspend fun search(
        @Query("keyword") keyword: String
    ): Account

    /*
    Search with a specific keyword
    */
    @GET("api/v1/tx")
    suspend fun getTransaction(
        @Query("hash") hash: String
    ): Account

    /*
    Get executed transactions
    */
    @GET("api/v1/txs")
    suspend fun getTransactions(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Account

    /*
    Get executed transactions
    */
    @POST("api/v1/sendTx")
    suspend fun sendTransaction(
        @Body requestSendTx: RequestSendTx,
    ): Account
}