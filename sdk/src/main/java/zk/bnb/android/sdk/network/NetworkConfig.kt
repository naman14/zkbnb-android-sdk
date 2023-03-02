package zk.bnb.android.sdk.network

import com.google.gson.Gson

data class NetworkConfig(
    val baseApiUrl: String,
    val gson: Gson = Gson(),
    val timeout: Long = 30L, // in seconds
    val isDebug: Boolean = false
)