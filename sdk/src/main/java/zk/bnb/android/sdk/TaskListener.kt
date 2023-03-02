package zk.bnb.android.sdk

interface TaskListener<T> {
    fun onSuccess(data: T)
    fun onError(e: Throwable)
}