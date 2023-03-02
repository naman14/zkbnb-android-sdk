package zk.bnb.android.sdk

enum class SupportedAPIs(val desc: String) {
    GET_NETWORK_STATUS("Get status of zkbnb"),
    GET_ACCOUNT("Get account by account's name, index or pk"),
    GET_ACCOUNT_PENDING_TXS("Get pending transactions of a specific account"),
    GET_ACCOUNT_NFTS("Get nfts of a specific account"),
    GET_ACCOUNTS("Get accounts"),
    GET_ASSET("Get asset"),
    GET_ASSETS("Get assets")
}