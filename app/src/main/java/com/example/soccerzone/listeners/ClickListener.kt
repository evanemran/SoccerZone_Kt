package com.example.soccerzone.listeners

interface ClickListener<T> {
    fun onClicked(data: T)
}