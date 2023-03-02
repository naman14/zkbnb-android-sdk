package zk.bnb.android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import zk.bnb.android.databinding.ActivityMainBinding
import zk.bnb.android.sdk.SupportedAPIs
import zk.bnb.android.sdk.ZkBnb
import zk.bnb.android.sdk.network.NetworkConfig

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val PREF_BASE_URL = "base_api_url"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportActionBar?.hide()

        val prefs = getPreferences(Context.MODE_PRIVATE)

        val savedApiUrl = prefs.getString(PREF_BASE_URL, "") ?: ""

        if (savedApiUrl.isEmpty()) {
            binding.etBaseUrl.hint = "Enter base api url"
            binding.btnSaveUrl.isVisible = true
        } else {
            binding.etBaseUrl.setText(savedApiUrl)
            binding.btnSaveUrl.isVisible = false

            initZkBnb(savedApiUrl)
        }

        binding.etBaseUrl.addTextChangedListener {
            binding.btnSaveUrl.isVisible = it!!.toString() != savedApiUrl
        }

        binding.btnSaveUrl.setOnClickListener {
            val newUrl = binding.etBaseUrl.text.toString()
            prefs.edit().putString(PREF_BASE_URL, newUrl).apply()
            binding.btnSaveUrl.isVisible = false

            initZkBnb(newUrl)
        }

        binding.etSearch.addTextChangedListener {
            showApis(it.toString())
        }

        showApis()
    }

    private fun initZkBnb(baseUrl: String) {
        ZkBnb.init(
            applicationContext, NetworkConfig(
                baseApiUrl = baseUrl,
                isDebug = true
            )
        )
        ZkBnb.attach(lifecycle)
    }

    private fun showApis(filter: String = "") {
        binding.apiDemoView.removeAllViews()
        SupportedAPIs.values().filter {
            if (filter.isNotEmpty()) {
                it.desc.contains(filter)
            } else {
                true
            }
        }
            .forEach {
                val apiView = ApiView(this)
                apiView.setup(it)
                binding.apiDemoView.addView(apiView)
            }
    }
}