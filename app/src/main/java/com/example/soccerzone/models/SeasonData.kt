package com.example.soccerzone.models

import java.io.Serializable

class SeasonData(var id: Int, var idLeague: Int, var leagueName: String, var start: Int, var end: Int)
    : Serializable{
}