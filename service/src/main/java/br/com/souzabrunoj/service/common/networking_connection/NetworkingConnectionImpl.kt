package br.com.souzabrunoj.service.common.networking_connection

import android.content.Context
import br.com.souzabrunoj.service.common.isOnline

class NetworkingConnectionImpl(private val context: Context): NetworkingConnection {
    override fun isOnline() = context.isOnline()
}