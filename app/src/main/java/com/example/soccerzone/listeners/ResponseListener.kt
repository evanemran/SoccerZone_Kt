package com.example.soccerzone.listeners

interface ResponseListener<T> {
    fun didFetch(response: T, message: String)
    fun didError(message: String)
}