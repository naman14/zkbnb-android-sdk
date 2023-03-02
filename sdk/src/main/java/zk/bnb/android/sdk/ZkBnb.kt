package zk.bnb.android.sdk

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.*
import zk.bnb.android.sdk.models.Account
import zk.bnb.android.sdk.models.request.AccountRequestType
import zk.bnb.android.sdk.models.NetworkStatus
import zk.bnb.android.sdk.network.NetworkConfig
import zk.bnb.android.sdk.network.NetworkFactory
import zk.bnb.android.sdk.network.ZkBnbApiService

@SuppressLint("StaticFieldLeak")
object ZkBnb {

    private const val TAG = "ZkBnb"

    private lateinit var appContext: Context
    private var lifecycle: Lifecycle? = null

    private lateinit var apiService: ZkBnbApiService

    /*
    provide a initialisation context needed to setup sdk
     */
    fun init(context: Context, networkConfig: NetworkConfig) {
        // using application context only for global initialisation to avoid any context leaks
        this.appContext = context.applicationContext

        if (networkConfig.baseApiUrl.isEmpty()) {
            throw IllegalArgumentException("Invalid base api url provided")
        }

        setup(networkConfig)
    }

    private fun setup(networkConfig: NetworkConfig) {
        val retrofit = NetworkFactory(networkConfig).retrofitApi
        apiService = retrofit.create(ZkBnbApiService::class.java)
    }

    /*
    attach a lifecycle if required. If lifecycle is attached, then all sdk calls will follow
    the underlying lifecycle events. (e.g. if activity lifecycle owner provided, then any pending
    network request will be cancelled as soon as the activity is destroyed)
     */
    fun attach(lifecycle: Lifecycle) {
        this.lifecycle = lifecycle
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun lifecycleScope(): CoroutineScope {
        if (lifecycle != null) {
            return lifecycle!!.coroutineScope
        } else {
            Log.d(TAG, "Warning: No lifecycle attached")
            return GlobalScope
        }
    }

    private fun <T> launchApiRequest(taskListener: TaskListener<T>, handler: suspend () -> Unit) {
        lifecycleScope().launch(Dispatchers.IO + CoroutineExceptionHandler { context, throwable ->
            lifecycleScope().launch(Dispatchers.Main) {
                taskListener.onError(throwable)
            }
        }) {
           handler()
        }
    }

    fun getNetworkStatus(taskListener: TaskListener<NetworkStatus>) {
        launchApiRequest(taskListener) {
            val status = apiService.status()
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(status)
            }
        }
    }

    fun getAccount(accountRequestType: AccountRequestType, value: String, taskListener: TaskListener<Account>) {
        launchApiRequest(taskListener) {
            val account = apiService.getAccount(accountRequestType.name.lowercase(), value)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

}