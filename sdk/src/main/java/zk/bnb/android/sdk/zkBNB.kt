package zk.bnb.android.sdk

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.*
import zk.bnb.android.sdk.models.*
import zk.bnb.android.sdk.models.request.AccountRequestType
import zk.bnb.android.sdk.models.request.AssetRequestType
import zk.bnb.android.sdk.models.request.BlockRequestType
import zk.bnb.android.sdk.models.request.RequestSendTx
import zk.bnb.android.sdk.network.NetworkConfig
import zk.bnb.android.sdk.network.NetworkFactory

/**
 * Singleton class to interact with zkbnb sdk.
 */
@SuppressLint("StaticFieldLeak")
object zkBNB {

    private const val TAG = "ZkBnb"

    private lateinit var appContext: Context
    private var lifecycle: Lifecycle? = null

    private lateinit var apiService: zkBNBApiService

    /**
     * initialise the zkbnb sdk. Should be called once at app start
     *
     * @param context application context
     * @param networkConfig Network configuration used in sdk setup
     */
    fun init(context: Context, networkConfig: NetworkConfig) {
        // using application context only for global initialisation to avoid any context leaks
        this.appContext = context.applicationContext

        if (networkConfig.baseApiUrl.isEmpty()) {
            throw IllegalArgumentException("Invalid base api url provided")
        }

        setup(networkConfig)
    }

    /**
     * Attach a lifecycle. If lifecycle is attached, then all sdk api calls will follow
     * the underlying lifecycle events. (e.g. if activity lifecycle owner provided, then any pending
     * network request will be cancelled as soon as the activity is destroyed)
     *
     * attaching a lifecycle is not required but highly recommended. If lifecycle is not attached, then
     * api calls will follow application wide lifecycle events
     *
     * @param lifecycle
     */
    fun attach(lifecycle: Lifecycle) {
        this.lifecycle = lifecycle
    }

    private fun setup(networkConfig: NetworkConfig) {
        val retrofit = NetworkFactory(networkConfig).retrofitApi
        apiService = retrofit.create(zkBNBApiService::class.java)
    }


    /**
     * get the relevant lifecycle scope. If lifecycle is attached, then use the lifecycle provided
     * else use GlobalScope application wide lifecycle
     *
     * @return lifecycleScope to be used in api calls
     */
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

    /**
     * get network status of zkbnb
     *
     * @param taskListener
     */
    fun getNetworkStatus(taskListener: TaskListener<NetworkStatus>) {
        launchApiRequest(taskListener) {
            val status = apiService.status()
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(status)
            }
        }
    }

    /**
     * Get account by account's name, index or pk
     *
     * @param accountRequestType accountRequestType, must be one of AccountRequestType.NAME, AccountRequestType.Index
     * or AccountRequestType.PK
     * @param value value of the account request type
     * @param taskListener
     */
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

    /**
     * Get pending transactions of a specific account
     *
     * @param accountRequestType accountRequestType, must be one of AccountRequestType.NAME, AccountRequestType.Index
     * or AccountRequestType.PK
     * @param value value of the account request type
     * @param taskListener
     */
    fun getAccountPendingTxs(
        accountRequestType: AccountRequestType,
        value: String,
        taskListener: TaskListener<Transactions>
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
        taskListener: TaskListener<Nfts>
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

    fun getAccountTxs(
        accountRequestType: AccountRequestType,
        value: String,
        offset: Int,
        limit: Int,
        taskListener: TaskListener<Transactions>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getAccountTxs(accountRequestType.name.lowercase(), value, offset, limit)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getAccounts(
        offset: Int,
        limit: Int,
        taskListener: TaskListener<Accounts>
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
        taskListener: TaskListener<Asset>
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
        taskListener: TaskListener<Assets>
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

    fun getBlock(
        blockRequestType: BlockRequestType,
        value: String,
        taskListener: TaskListener<Block>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getBlock(blockRequestType.name.lowercase(), value)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getBlockTxs(
        blockRequestType: BlockRequestType,
        value: String,
        taskListener: TaskListener<Transactions>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getBlockTxs(blockRequestType.name.lowercase(), value)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getBlocks(
        offset: Int,
        limit: Int,
        taskListener: TaskListener<Blocks>
    ) {
        launchApiRequest(taskListener) {
            validateOffsetAndLimit(offset, limit)
            val account =
                apiService.getBlocks(offset, limit)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getCurrentHeight(
        taskListener: TaskListener<CurrentHeight>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getCurrentHeight()
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getGasAccount(
        taskListener: TaskListener<GasAccount>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getGasAccount()
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getGasFee(
        assetId: Int,
        txType: Int,
        taskListener: TaskListener<GasFee>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getGasFee(assetId, txType)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getGasFeeAssets(
        taskListener: TaskListener<GasFeeAssets>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getGasFeeAssets()
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getLayer2BasicInfo(
        taskListener: TaskListener<Layer2BasicInfo>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getLayer2BasicInfo()
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getMaxOfferId(
        accountIndex: Int,
        taskListener: TaskListener<MaxOfferId>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getMaxOfferId(accountIndex)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getPendingTransactions(
        offset: Int,
        limit: Int,
        taskListener: TaskListener<Transactions>
    ) {
        launchApiRequest(taskListener) {
            validateOffsetAndLimit(offset, limit)
            val account =
                apiService.getPendingTransactions(offset, limit)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getExecutedTransactions(
        offset: Int,
        limit: Int,
        fromHash: String?,
        taskListener: TaskListener<Transactions>
    ) {
        launchApiRequest(taskListener) {
            validateOffsetAndLimit(offset, limit)
            val account =
                apiService.getExecutedTransactions(offset, limit, fromHash)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getNextNonce(
        accountIndex: Int,
        taskListener: TaskListener<NextNonce>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getNextNonce(accountIndex)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun search(
        keyword: String,
        taskListener: TaskListener<Search>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.search(keyword)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getTransactionByHash(
        hash: String,
        taskListener: TaskListener<EnrichedTx>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.getTransaction(hash)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun getTransactions(
        offset: Int,
        limit: Int,
        taskListener: TaskListener<Transactions>
    ) {
        launchApiRequest(taskListener) {
            validateOffsetAndLimit(offset, limit)
            val account =
                apiService.getTransactions(offset, limit)
            withContext(Dispatchers.Main) {
                taskListener.onSuccess(account)
            }
        }
    }

    fun sendTransaction(
        requestSendTx: RequestSendTx,
        taskListener: TaskListener<TxHash>
    ) {
        launchApiRequest(taskListener) {
            val account =
                apiService.sendTransaction(requestSendTx)
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