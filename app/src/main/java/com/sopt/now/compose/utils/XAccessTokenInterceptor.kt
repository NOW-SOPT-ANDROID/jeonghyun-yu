package com.sopt.now.compose.utils



import com.sopt.now.compose.ApplicationClass.SharedPreferences.sSharedPreferences
import com.sopt.now.compose.utils.Constants.Companion.MEMBER_ID
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sSharedPreferences.getString(MEMBER_ID, null)
        if (jwtToken != null) {
            builder.addHeader(MEMBER_ID, jwtToken)
        }
        return chain.proceed(builder.build())
    }
}