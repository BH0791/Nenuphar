package fr.hamtec.nenuphar

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

object VolleyClient {
    private var queue: RequestQueue? = null

    fun init(context: Context) {
        queue = Volley.newRequestQueue(context.applicationContext)
    }

    fun getQueue(): RequestQueue {
        return queue ?: throw IllegalStateException("Volley not initialized")
    }
}