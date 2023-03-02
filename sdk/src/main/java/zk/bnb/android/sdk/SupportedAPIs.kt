package zk.bnb.android.sdk

enum class SupportedAPIs(val desc: String) {
    GET_NETWORK_STATUS("Get status of zkbnb"),
    GET_ACCOUNT("Get account by account's name, index or pk"),
    GET_ACCOUNT_PENDING_TXS("Get pending transactions of a specific account"),
    GET_ACCOUNT_NFTS("Get nfts of a specific account"),
    GET_ACCOUNT_TRANSACTIONS("Get transactions of a specific account"),
    GET_ACCOUNTS("Get accounts"),
    GET_ASSET("Get asset"),
    GET_ASSETS("Get assets"),
    GET_BLOCK("Get block by its height or commitment"),
    GET_BLOCK_TXS("Get transactions in a block"),
    GET_BLOCKS("Get blocks"),
    GET_CURRENT_HEIGHT("Get current height"),
    GET_GAS_ACCOUNT("Get gas account, who will charge gas fees for transactions"),
    GET_GAS_FEE("Get gas fee amount for using a specific asset as gas asset"),
    GET_GAS_FEE_ASSETS("Get supported gas fee assets"),
    GET_LAYER2_BASIC_INFO("Get zkbnb general info, including contract address, and count of transactions and active users"),
    GET_MAX_OFFER_ID("Get max nft offer id for a specific account"),
    GET_PENDING_TXS("Get pending transactions"),
    GET_EXECUTED_TXS("Get executed transactions"),
    GET_NEXT_NONCE("Get next nonce"),
    SEARCH("Search with a specific keyword"),
    GET_TRANSACTION_BY_HASH("Get transaction by hash"),
    GET_TRANSACTIONS("Get transactions"),
    SEND_TRANSACTION("Send raw transaction")
}