package com.cythero.data.utils


/**
 * enum is not used because it is 'not compile time compliant' so I had to resort to using this, it is recommended to get the
 * fields using the interface [UrlsGetFields] where possible since it offers more flexibility
 */
@Suppress("ClassName")
sealed class Urls: UrlsGetFields {
    object API: Urls() {
        const val baseUrlLocal = "http://192.168.1.150:8080"
        @Suppress("MemberVisibilityCanBePrivate")
        const val appendedUrlLocal = "http://192.168.1.150:8080"
        override fun getBaseUrl(): String = baseUrlLocal
        override fun getAppendedUrl(): String = appendedUrlLocal
    }
    @Suppress("MemberVisibilityCanBePrivate")
    object CITY: Urls() {
        const val baseUrlLocal = "cities-service/api"
        const val appendedUrlLocal = "${API.baseUrlLocal}/$baseUrlLocal"
        override fun getBaseUrl(): String = baseUrlLocal
        override fun getAppendedUrl(): String = appendedUrlLocal
    }
    @Suppress("MemberVisibilityCanBePrivate")
    object ATTRACTION: Urls() {
        const val baseUrlLocal = "attractions-service/api"
        const val appendedUrlLocal = "${API.baseUrlLocal}/$baseUrlLocal"
        override fun getBaseUrl(): String = baseUrlLocal
        override fun getAppendedUrl(): String = appendedUrlLocal
    }
}

interface UrlsGetFields {

    fun getBaseUrl(): String

    fun getAppendedUrl(): String

}