package br.com.souzabrunoj.service.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import br.com.souzabrunoj.domain.common.getStringFromResources
import br.com.souzabrunoj.domain.data.response.BaseResponse
import br.com.souzabrunoj.service.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.net.UnknownHostException

@Suppress("DEPRECATION")
fun Context.isOnline(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        val ni = cm.activeNetworkInfo ?: return false
        ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI || ni.type == ConnectivityManager.TYPE_MOBILE)
    } else {
        cm.getNetworkCapabilities(cm.activeNetwork)?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        } ?: false
    }
}

fun Throwable.toNetworkingError(): NetworkingError {
    return when (this) {
        is HttpException -> this.toNetworkingError()
        is UnknownHostException -> NetworkingError(this.toErrorDescription())
        else -> NetworkingError(message = this.message, originalError = this)
    }
}

private fun HttpException.toNetworkingError(): NetworkingError {
    val errorResponse = response()?.errorBody()?.string()?.run { jsonTo<BaseResponse>() }

    return NetworkingError(
        message = message(),
        errorCode = errorResponse?.getErrorCode(),
        httpCode = code(),
        errorDescription = errorResponse?.getErrorMessage() ?: code().toHttpCodeDescriptionIfNeeded(),
        httpErrorDescription = message,
        originalError = this
    )
}

private fun Throwable.toErrorDescription(): String? {
    return when (this) {
        is NetworkConnectionException -> getStringFromResources(R.string.network_no_internet_connection)
        is UnknownHostException -> getStringFromResources(R.string.generic_error_message)
        else -> getStringFromResources(R.string.generic_error_message)
    }
}

private fun Int.toHttpCodeDescriptionIfNeeded(): String? {
    return when (this) {
        in 0..499 -> getStringFromResources(R.string.generic_error_message)
        in 500..599 -> getStringFromResources(R.string.internal_server_error)
        else -> null
    }
}

inline fun <reified T> String.jsonTo(): T? {
    return try {
        Gson().fromJson(this, object : TypeToken<T>() {}.type)
    } catch (e: Throwable) {
        e.printStackTrace()
        null
    }
}