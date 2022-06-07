package com.example.soccerzone.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccerzone.R
import com.example.soccerzone.adapters.FixturesRecyclerAdapter
import com.example.soccerzone.adapters.SeasonsRecyclerAdapter
import com.example.soccerzone.listeners.ClickListener
import com.example.soccerzone.listeners.ResponseListener
import com.example.soccerzone.manager.RequestManager
import com.example.soccerzone.models.Fixture
import com.example.soccerzone.models.FixtureResponse
import com.example.soccerzone.models.SeasonData
import com.example.soccerzone.models.SeasonsResponse
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailsActivity : AppCompatActivity() {
    lateinit var manager: RequestManager
    var SeasonData: SeasonData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        SeasonData = intent.getSerializableExtra("data") as SeasonData?

        manager = RequestManager(this)
        manager.getAllFixtures(fixtureResponseListener, SeasonData!!.id, 1)

    }
    private val fixtureResponseListener: ResponseListener<FixtureResponse> = object :
        ResponseListener<FixtureResponse> {
        override fun didFetch(response: FixtureResponse, message: String) {
            recycler_details.setHasFixedSize(true)
            recycler_details.layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.VERTICAL, false)
            var fixtureAdapter: FixturesRecyclerAdapter = FixturesRecyclerAdapter(this@DetailsActivity, response.data, itemClickListener)
            recycler_details.adapter = fixtureAdapter
        }

        override fun didError(message: String) {
            Toast.makeText(this@DetailsActivity, message, Toast.LENGTH_LONG).show()
        }

    }

    private val itemClickListener: ClickListener<Fixture> = object : ClickListener<Fixture> {
        override fun onClicked(data: Fixture) {
            Toast.makeText(this@DetailsActivity, data.awayName, Toast.LENGTH_LONG).show()
        }

    }

}