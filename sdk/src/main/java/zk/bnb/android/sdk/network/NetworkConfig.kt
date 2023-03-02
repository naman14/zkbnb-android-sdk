package zk.bnb.android.sdk.network

import com.google.gson.Gson

/**
 * NetworkConfig to be used when setting up the sdk
 *
 * @property baseApiUrl (Required) Base API url of the zkbnb api server
 * @property gson (Optional) provide a custom gson instance to decode reponse data
 * @property timeout (Optional) connect, read and write timeouts in seconds
 * @property isDebug (Optional) if true, logs API calls to android logcat
 */
data class NetworkConfig(
    val baseApiUrl: String,
    val gson: Gson = Gson(),
    val timeout: Long = 30L, // in seconds
    val isDebug: Boolean = false
)