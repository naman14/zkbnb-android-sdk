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
import zk.bnb.android.sdk.models.request.AssetRequestType
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
                throwable.printStackTrace()
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

    fun getAccount(
        accountRequestType: AccountRequestType,
        value: String,
        taskListener: TaskListener<Account>
    ) {
        launchApiRequest(taskListener) {
            val account = apiService.getAccount(accountRequestType.name.lowercase(), value)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getAccountPendingTxs(
        accountRequestType: AccountRequestType,
        value: String,
        taskListener: TaskListener<Account>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getAccountPendingTxs(accountRequestType.name.lowercase(), value)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getAccountNfts(
        accountRequestType: AccountRequestType,
        value: String,
        offset: Int,
        limit: Int,
        taskListener: TaskListener<Account>
    ) {
        launchApiRequest(taskListener) {
            validateOffsetAndLimit(offset, limit)
            val account =
                apiService.getAccountNfts(accountRequestType.name.lowercase(), value, offset, limit)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getAccounts(
        offset: Int,
        limit: Int,
        taskListener: TaskListener<Account>
    ) {
        launchApiRequest(taskListener) {
            validateOffsetAndLimit(offset, limit)
            val account =
                apiService.getAccounts(offset, limit)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getAsset(
        assetRequestType: AssetRequestType,
        value: String,
        taskListener: TaskListener<Account>
    ) {
        launchApiRequest(taskListener) {
            val account = apiService.getAsset(assetRequestType.name.lowercase(), value)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getAssets(
        offset: Int,
        limit: Int,
        taskListener: TaskListener<Account>
    ) {
        launchApiRequest(taskListener) {
            validateOffsetAndLimit(offset, limit)
            val account =
                apiService.getAssets(offset, limit)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    private fun validateOffsetAndLimit(offset: Int, limit: Int) {
        if (offset < 0 || offset > 100000) {
            throw IllegalArgumentException("Invalid offset value, offset should be between 0 and 100000")
        }
        if (limit < 1 || limit > 100) {
            throw IllegalArgumentException("Invalid limit value, limit should be between 1 and 100")
        }
    }

}