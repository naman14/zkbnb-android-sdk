package zk.bnb.android.sdk

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import zk.bnb.android.sdk.models.*
import zk.bnb.android.sdk.models.request.RequestSendTx

/**
 * Retrofit API interface for all zkbnb http APIs
 *
 */
interface zkBNBApiService {

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
    ): Transactions

    /*
   Get nfts of a specific account
    */
    @GET("api/v1/accountNfts")
    suspend fun getAccountNfts(
        @Query("by") by: String,
        @Query("value") value: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Nfts

    /*
    Get transactions of a specific account
     */
    @GET("api/v1/accountTxs")
    suspend fun getAccountTxs(
        @Query("by") by: String,
        @Query("value") value: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Transactions

    /*
    Get accounts
    */
    @GET("api/v1/accounts")
    suspend fun getAccounts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Accounts

    /*
    Get asset
    */
    @GET("api/v1/asset")
    suspend fun getAsset(
        @Query("by") by: String,
        @Query("value") value: String
    ): Asset

    /*
    Get assets
    */
    @GET("api/v1/assets")
    suspend fun getAssets(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Assets

    /*
    Get block by its height or commitment
    */
    @GET("api/v1/block")
    suspend fun getBlock(
        @Query("by") by: String,
        @Query("value") value: String
    ): Block

    /*
    Get transactions in a block
    */
    @GET("api/v1/blockTxs")
    suspend fun getBlockTxs(
        @Query("by") by: String,
        @Query("value") value: String
    ): Transactions

    /*
    Get blocks
    */
    @GET("api/v1/blocks")
    suspend fun getBlocks(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Blocks

    /*
    Get current height
    */
    @GET("api/v1/currentHeight")
    suspend fun getCurrentHeight(): CurrentHeight

    /*
    Get gas account, who will charge gas fees for transactions
    */
    @GET("api/v1/gasAccount")
    suspend fun getGasAccount(): GasAccount

    /*
    Get gas fee amount for using a specific asset as gas asset
    */
    @GET("api/v1/gasFee")
    suspend fun getGasFee(
        @Query("asset_id") assetId: Int,
        @Query("tx_type") txType: Int
    ): GasFee

    /*
    Get gas fee amount for using a specific asset as gas asset
    */
    @GET("api/v1/gasFeeAssets")
    suspend fun getGasFeeAssets(): GasFeeAssets

    /*
    Get zkbnb general info, including contract address, and count of transactions and active users
    */
    @GET("api/v1/layer2BasicInfo")
    suspend fun getLayer2BasicInfo(): Layer2BasicInfo

    /*
    Get zkbnb general info, including contract address, and count of transactions and active users
    */
    @GET("api/v1/maxOfferId")
    suspend fun getMaxOfferId(@Query("account_index") accountIndex: Int): MaxOfferId

    /*
    Get pending transactions
    */
    @GET("api/v1/pendingTxs")
    suspend fun getPendingTransactions(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Transactions

    /*
    Get executed transactions
    */
    @GET("api/v1/executedTxs")
    suspend fun getExecutedTransactions(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("from_hash") fromHash: String?
    ): Transactions

    /*
    Get next nonce
    */
    @GET("api/v1/nextNonce")
    suspend fun getNextNonce(
        @Query("account_index") accountIndex: Int
    ): NextNonce

    /*
    Search with a specific keyword
    */
    @GET("api/v1/search")
    suspend fun search(
        @Query("keyword") keyword: String
    ): Search

    /*
    Search with a specific keyword
    */
    @GET("api/v1/tx")
    suspend fun getTransaction(
        @Query("hash") hash: String
    ): EnrichedTx

    /*
    Get executed transactions
    */
    @GET("api/v1/txs")
    suspend fun getTransactions(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Transactions

    /*
    Get executed transactions
    */
    @POST("api/v1/sendTx")
    suspend fun sendTransaction(
        @Body requestSendTx: RequestSendTx,
    ): TxHash
}