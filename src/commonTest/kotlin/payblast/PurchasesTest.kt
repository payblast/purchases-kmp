package payblast

import kotlin.test.Test
import kotlin.test.assertTrue

class PurchasesTest {
    @Test
    fun httpClientReadsOfferingsAndRendersPackageKeys() {
        val http = object : HttpTransport {
            override fun get(path: String, apiKey: String): String {
                return """{"packages":[{"lookup_key":"monthly"}]}"""
            }

            override fun post(path: String, apiKey: String, body: String): String = body
        }
        val purchases = Purchases("http://127.0.0.1:4000", http)
        purchases.configure("pk_test", "user-1")
        assertTrue(purchases.getOfferings().contains("monthly"))
        val rendered = purchases.presentPaywall("{}", listOf("monthly", "annual"))
        assertTrue(rendered.contains("monthly"))
        assertTrue(rendered.contains("annual"))
    }
}
