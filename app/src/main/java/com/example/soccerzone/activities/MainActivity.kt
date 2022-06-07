package com.example.soccerzone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccerzone.R
import com.example.soccerzone.adapters.SeasonsRecyclerAdapter
import com.example.soccerzone.listeners.ClickListener
import com.example.soccerzone.listeners.ResponseListener
import com.example.soccerzone.manager.RequestManager
import com.example.soccerzone.models.SeasonData
import com.example.soccerzone.models.SeasonsResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mSeasonsRecyclerAdapter: SeasonsRecyclerAdapter
    lateinit var manager: RequestManager
    var seasonList: MutableList<SeasonData> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = RequestManager(this)
        manager.getAllSeasons(seasonResponseListener)
    }

    private val seasonResponseListener: ResponseListener<SeasonsResponse> = object : ResponseListener<SeasonsResponse>{
        override fun didFetch(response: SeasonsResponse, message: String) {
            seasonList.add(0, SeasonData(1331, 71, "World Cup - 2022 Qatar", 2022, 2022))
            seasonList.addAll(response.data)
            recycler_seasons.setHasFixedSize(true)
            recycler_seasons.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            mSeasonsRecyclerAdapter = SeasonsRecyclerAdapter(this@MainActivity, seasonList, itemClickListener)
            recycler_seasons.adapter = mSeasonsRecyclerAdapter
        }

        override fun didError(message: String) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }

    }

    private val itemClickListener: ClickListener<SeasonData> = object : ClickListener<SeasonData>{
        override fun onClicked(data: SeasonData) {
            startActivity(Intent(this@MainActivity, DetailsActivity::class.java)
                .putExtra("data", data))
        }

    }
}