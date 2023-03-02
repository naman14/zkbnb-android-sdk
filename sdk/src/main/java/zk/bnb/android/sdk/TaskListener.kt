package zk.bnb.android.sdk

/**
 * Helper interface to manage success and error callbacks when interacting with the sdk
 *
 * @param T type of response object
 */
interface TaskListener<T> {
    fun onSuccess(data: T)
    fun onError(e: Throwable)
}