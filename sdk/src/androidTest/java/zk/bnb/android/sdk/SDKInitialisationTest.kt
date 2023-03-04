package zk.bnb.android.sdk

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import zk.bnb.android.sdk.network.NetworkConfig

@RunWith(AndroidJUnit4::class)
class SDKInitialisationTest {

    @Test(expected = IllegalArgumentException::class)
    fun invalidBaseUrl() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("zk.bnb.android.sdk.test", appContext.packageName)

        ZkBNB.init(appContext, NetworkConfig(
            baseApiUrl = "",
            isDebug = false
        ))
    }

    @Test
    fun validNetworkConfig() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("zk.bnb.android.sdk.test", appContext.packageName)

        ZkBNB.init(appContext, NetworkConfig(
            baseApiUrl = "http://192.168.0.1:8888",
            isDebug = false
        ))
    }
}