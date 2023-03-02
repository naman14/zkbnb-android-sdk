package zk.bnb.android

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.google.gson.GsonBuilder
import zk.bnb.android.databinding.ItemApiDemoBinding
import zk.bnb.android.databinding.ViewApiParamBinding
import zk.bnb.android.sdk.TaskListener
import zk.bnb.android.sdk.ZkBnb
import zk.bnb.android.sdk.models.Account
import zk.bnb.android.sdk.models.NetworkStatus
import zk.bnb.android.sdk.models.request.AccountRequestType

class ApiView(context: Context): LinearLayout(context) {

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
                addParam(AccountRequestType::javaClass.name, "name/index/pk")
                addParam("value", "value of name/index/pk")
                binding.btnExecute.setOnClickListener {
                    binding.progressBar.isVisible = true
                    ZkBnb.getAccount(AccountRequestType.valueOf(getParamValue(AccountRequestType::javaClass.name)), getParamValue("value"), object : TaskListener<Account> {
                        override fun onError(e: Throwable) {
                           showError(e)
                        }

                        override fun onSuccess(data: Account) {
                            showResponse(data)

                        }
                    })
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

    private inline fun <reified T>T.logTag() = T::class.java.simpleName

}