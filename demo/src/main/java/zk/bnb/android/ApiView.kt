package zk.bnb.android

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.google.gson.GsonBuilder
import zk.bnb.android.databinding.ItemApiDemoBinding
import zk.bnb.android.databinding.ViewApiParamBinding
import zk.bnb.android.sdk.SupportedAPIs
import zk.bnb.android.sdk.TaskListener
import zk.bnb.android.sdk.ZkBnb
import zk.bnb.android.sdk.models.*
import zk.bnb.android.sdk.models.request.AccountRequestType
import zk.bnb.android.sdk.models.request.AssetRequestType
import zk.bnb.android.sdk.models.request.BlockRequestType
import zk.bnb.android.sdk.models.request.RequestSendTx

class ApiView(context: Context) : LinearLayout(context) {

    private var binding: ItemApiDemoBinding
    private val gson = GsonBuilder().setPrettyPrinting().create()

    init {
        orientation = LinearLayout.VERTICAL
        binding = ItemApiDemoBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }


    fun setup(api: SupportedAPIs) {
        binding.tvApiDesc.text = api.desc
        when (api) {
            SupportedAPIs.GET_NETWORK_STATUS -> {
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    ZkBnb.getNetworkStatus(object : TaskListener<NetworkStatus> {
                        override fun onError(e: Throwable) {
                            showError(e)
                        }

                        override fun onSuccess(data: NetworkStatus) {
                            showResponse(data)
                        }
                    })
                }
            }
            SupportedAPIs.GET_ACCOUNT -> {
                addParam(AccountRequestType::class.java.simpleName, "name/index/pk")
                addParam("value", "value of name/index/pk")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getAccount(
                            AccountRequestType.valueOf(getParamValue(AccountRequestType::class.java.simpleName).uppercase()),
                            getParamValue("value"),
                            object : TaskListener<Account> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Account) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_ACCOUNT_PENDING_TXS -> {
                addParam(AccountRequestType::class.java.simpleName, "name/index/pk")
                addParam("value", "value of name/index/pk")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getAccountPendingTxs(AccountRequestType.valueOf(
                            getParamValue(
                                AccountRequestType::class.java.simpleName
                            ).uppercase()
                        ),
                            getParamValue("value"), object : TaskListener<Transactions> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Transactions) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_ACCOUNT_NFTS -> {
                addParam(AccountRequestType::class.java.simpleName, "name/index/pk")
                addParam("value", "value of name/index/pk")
                addParam("offset", "offset")
                addParam("limit", "limit")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getAccountNfts(AccountRequestType.valueOf(
                            getParamValue(
                                AccountRequestType::class.java.simpleName
                            ).uppercase()
                        ),
                            getParamValue("value"),
                            getParamValue("offset").toInt(),
                            getParamValue("limit").toInt(),
                            object : TaskListener<Nfts> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Nfts) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_ACCOUNT_TRANSACTIONS -> {
                addParam(AccountRequestType::class.java.simpleName, "name/index/pk")
                addParam("value", "value of name/index/pk")
                addParam("offset", "offset")
                addParam("limit", "limit")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getAccountTxs(AccountRequestType.valueOf(
                            getParamValue(
                                AccountRequestType::class.java.simpleName
                            ).uppercase()
                        ),
                            getParamValue("value"),
                            getParamValue("offset").toInt(),
                            getParamValue("limit").toInt(),
                            object : TaskListener<Transactions> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Transactions) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_ACCOUNTS -> {
                addParam("offset", "offset")
                addParam("limit", "limit")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getAccounts(
                            getParamValue("offset").toInt(),
                            getParamValue("limit").toInt(),
                            object : TaskListener<Accounts> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Accounts) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_ASSET -> {
                addParam(AssetRequestType::class.java.simpleName, "id/symbol")
                addParam("value", "value of id/symbol")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getAsset(
                            AssetRequestType.valueOf(getParamValue(AssetRequestType::class.java.simpleName).uppercase()),
                            getParamValue("value"),
                            object : TaskListener<Asset> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Asset) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_ASSETS -> {
                addParam("offset", "offset")
                addParam("limit", "limit")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getAssets(
                            getParamValue("offset").toInt(),
                            getParamValue("limit").toInt(),
                            object : TaskListener<Assets> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Assets) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_BLOCK -> {
                addParam(BlockRequestType::class.java.simpleName, "height/commitment")
                addParam("value", "value of height/commitment")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getBlock(
                            BlockRequestType.valueOf(getParamValue(BlockRequestType::class.java.simpleName).uppercase()),
                            getParamValue("value"),
                            object : TaskListener<Block> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Block) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_BLOCK_TXS -> {
                addParam(BlockRequestType::class.java.simpleName, "height/commitment")
                addParam("value", "value of height/commitment")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getBlockTxs(
                            BlockRequestType.valueOf(getParamValue(BlockRequestType::class.java.simpleName).uppercase()),
                            getParamValue("value"),
                            object : TaskListener<Transactions> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Transactions) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_BLOCKS -> {
                addParam("offset", "offset")
                addParam("limit", "limit")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getBlocks(
                            getParamValue("offset").toInt(),
                            getParamValue("limit").toInt(),
                            object : TaskListener<Blocks> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Blocks) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_CURRENT_HEIGHT -> {
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getCurrentHeight(
                            object : TaskListener<CurrentHeight> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: CurrentHeight) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_GAS_ACCOUNT -> {
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getGasAccount(
                            object : TaskListener<GasAccount> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: GasAccount) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_GAS_FEE -> {
                addParam("assetId", "assetId (int)")
                addParam("txType", "txType (int)")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getGasFee(
                            getParamValue("assetId").toInt(),
                            getParamValue("txType").toInt(),
                            object : TaskListener<GasFee> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: GasFee) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_GAS_FEE_ASSETS -> {
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getGasFeeAssets(
                            object : TaskListener<GasFeeAssets> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: GasFeeAssets) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_LAYER2_BASIC_INFO -> {
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getLayer2BasicInfo(
                            object : TaskListener<Layer2BasicInfo> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Layer2BasicInfo) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_MAX_OFFER_ID -> {
                addParam("accountIndex", "accountIndex (int)")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getMaxOfferId(
                            getParamValue("accountIndex").toInt(),
                            object : TaskListener<MaxOfferId> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: MaxOfferId) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_PENDING_TXS -> {
                addParam("offset", "offset")
                addParam("limit", "limit")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getPendingTransactions(
                            getParamValue("offset").toInt(),
                            getParamValue("limit").toInt(),
                            object : TaskListener<Transactions> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Transactions) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_EXECUTED_TXS -> {
                addParam("offset", "offset")
                addParam("limit", "limit")
                addParam("fromHash", "fromHash (optional)")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getExecutedTransactions(
                            getParamValue("offset").toInt(),
                            getParamValue("limit").toInt(),
                            getParamValue("fromHash").ifEmpty { null },
                            object : TaskListener<Transactions> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Transactions) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_NEXT_NONCE -> {
                addParam("accountIndex", "accountIndex (int)")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getNextNonce(
                            getParamValue("accountIndex").toInt(),
                            object : TaskListener<NextNonce> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: NextNonce) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.SEARCH -> {
                addParam("keyword", "keyword")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.search(
                            getParamValue("keyword"),
                            object : TaskListener<Search> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Search) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_TRANSACTION_BY_HASH -> {
                addParam("hash", "tx hash")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getTransactionByHash(
                            getParamValue("hash"),
                            object : TaskListener<EnrichedTx> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: EnrichedTx) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.GET_TRANSACTIONS -> {
                addParam("offset", "offset")
                addParam("limit", "limit")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.getTransactions(
                            getParamValue("offset").toInt(),
                            getParamValue("limit").toInt(),
                            object : TaskListener<Transactions> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: Transactions) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }
            SupportedAPIs.SEND_TRANSACTION -> {
                addParam("txType", "type of transaction (int)")
                addParam("txInfo", "tx info (string)")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    try {
                        ZkBnb.sendTransaction(
                            RequestSendTx(
                                getParamValue("txType").toInt(),
                                getParamValue("txInfo")
                            ),
                            object : TaskListener<TxHash> {
                                override fun onError(e: Throwable) {
                                    showError(e)
                                }

                                override fun onSuccess(data: TxHash) {
                                    showResponse(data)

                                }
                            })
                    } catch (e: Exception) {
                        showError(e)
                    }
                }
            }

            else -> {}
        }

    }

    private fun addParam(name: String, hint: String) {
        val apiParamView = ViewApiParamBinding.inflate(LayoutInflater.from(context))
        apiParamView.tvParamName.text = name
        apiParamView.tvParamValue.hint = hint
        apiParamView.tvParamValue.tag = name
        binding.viewParams.addView(apiParamView.root)
    }

    private fun getParamValue(name: String): String {
        return binding.viewParams.findViewWithTag<EditText>(name).text.toString()
    }

    private fun <T> showResponse(data: T) {
        binding.tvResponse.text = data.toString()
        binding.progressBar.isVisible = false
    }

    private fun showError(e: Throwable) {
        binding.tvResponse.text = e.message
        binding.progressBar.isVisible = false
    }

    private inline fun <reified T> T.logTag() = T::class.java.simpleName

}