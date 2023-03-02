package zk.bnb.android.sdk.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import zk.bnb.android.sdk.models.Account
import zk.bnb.android.sdk.models.NetworkStatus

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
}