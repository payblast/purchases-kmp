package payblast

interface HttpTransport {
    fun get(path: String, apiKey: String): String
    fun post(path: String, apiKey: String, body: String): String
}

class Purchases(
    private val baseUrl: String,
    private val http: HttpTransport,
) {
    private var apiKey: String = ""
    private var appUserId: String = ""

    fun configure(apiKey: String, appUserId: String) {
        this.apiKey = apiKey
        this.appUserId = appUserId
    }

    fun logIn(appUserId: String): String {
        val info = http.post(
            "$baseUrl/v1/customers/${this.appUserId}/login",
            apiKey,
            """{"new_app_user_id":"$appUserId"}""",
        )
        this.appUserId = appUserId
        return info
    }

    fun logOut() {
        appUserId = "\$payblastAnon"
    }

    fun getOfferings(): String = http.get("$baseUrl/v1/offerings", apiKey)

    fun purchase(productId: String): String = getCustomerInfo()

    fun restore(): String = getCustomerInfo()

    fun getCustomerInfo(): String = http.get("$baseUrl/v1/customers/$appUserId", apiKey)

    fun presentPaywall(document: String, packageKeys: List<String>): String {
        return document + " " + packageKeys.joinToString(" ")
    }
}
