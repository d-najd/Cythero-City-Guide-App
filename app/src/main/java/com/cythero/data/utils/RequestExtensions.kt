package com.cythero.data.utils

import com.cythero.presentation.util.event.BaseEvent
import com.cythero.presentation.util.event.EventFactory
import com.cythero.presentation.util.event.EventSendableHolder
import retrofit2.Call
import java.io.IOException


inline fun <reified T> Call<T>.processRequest(): T? {
    if(T::class.java == Void::class.java) throw IllegalStateException("Use Call<T>.processVoidRequest() for void requests")
    try {
        val response = execute()
        if(response.isSuccessful){
            return response.body()
        } else {
            EventSendableHolder.get().sendEvent(EventFactory.getErrorMessageByCode(response.code()))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        when(e){
            is IOException -> EventSendableHolder.get().sendEvent(BaseEvent.NetworkError)
            else -> throw e
        }
    }
    return null
}

/**
 * @return true for successful requests
 */
fun <T> Call<T>.processVoidRequest(): Boolean {
    try{
        val response = execute()
        if(response.isSuccessful){
            return true
        } else {
            EventSendableHolder.get().sendEvent(EventFactory.getErrorMessageByCode(response.code()))
        }
    } catch (e: Exception){
        e.printStackTrace()
        when(e){
            is IOException -> EventSendableHolder.get().sendEvent(BaseEvent.NetworkError)
            else -> throw e
        }
    }
    return false
}