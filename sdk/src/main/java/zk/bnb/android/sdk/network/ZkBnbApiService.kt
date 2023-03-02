package zk.bnb.android.sdk.network

import retrofit2.http.GET
import retrofit2.http.POST
import zk.bnb.android.sdk.models.Account
import zk.bnb.android.sdk.models.NetworkStatus

interface ZkBnbApiService {

    // Get status of zkbnb
    @GET(".")
    suspend fun status(): NetworkStatus

    // Get account by account's name, index or pk
    @GET("api/v1/account")
    suspend fun getAccount(by: String, value: String): Account
}