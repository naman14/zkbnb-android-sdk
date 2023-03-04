package zk.bnb.android.sdk

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import zk.bnb.android.sdk.models.NetworkStatus
import zk.bnb.android.sdk.network.NetworkConfig

@RunWith(AndroidJUnit4::class)
class NetworkErrorTest {

    @Test
    fun networkError() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("zk.bnb.android.sdk.test", appContext.packageName)

        ZkBNB.init(appContext, NetworkConfig(
            baseApiUrl = "http://111.111.111.111:8888", //incorrect api url
            isDebug = false
        ))

        ZkBNB.getNetworkStatus(object : TaskListener<NetworkStatus> {
            override fun onError(e: Throwable) {
                assert(true) { "Expected throwable. Test passed" }
            }

            override fun onSuccess(data: NetworkStatus) {
                    assert(false) { "Should have been error. Test failed" }
            }
        })
    }
}