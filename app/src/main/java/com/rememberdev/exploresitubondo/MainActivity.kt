package com.rememberdev.exploresitubondo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvDestination: RecyclerView

    private var list = ArrayList<Destination>()

    var showingMode: Boolean = false

    private lateinit var destinationData: Destination

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDestination = findViewById(R.id.rv_destinations)
        rvDestination.setHasFixedSize(true)

        list.addAll(DestinationDataObject.listDestination)
        showRecyclerList()

    }

    private fun showRecyclerList(){
        rvDestination.layoutManager = LinearLayoutManager(this)
        var listDestinationAdapter = ListDestinationAdapter(list)

        rvDestination.adapter = listDestinationAdapter

        listDestinationAdapter.setOnItemClickCallback(object : ListDestinationAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Destination) {
                goToDetailDestination(data)
            }
        })
    }

    private fun showRecyclerGrid(){
        rvDestination.layoutManager = GridLayoutManager(this, 2)
        var gridDestinationAdapter = GridDestinationAdapter(list)

        rvDestination.adapter = gridDestinationAdapter

        gridDestinationAdapter.setOnItemClickCallback(object : GridDestinationAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Destination) {
                goToDetailDestination(data)
            }
        })
    }

    fun goToDetailDestination(destination: Destination){
        val moveWithObjectIntent = Intent(this@MainActivity, DetailDestinationActivity::class.java)
        moveWithObjectIntent.putExtra(DetailDestinationActivity.EXTRA_DESTINATION, destination)
        startActivity(moveWithObjectIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_view_mode -> {
                if (!showingMode) {
                    showingMode = true
//                    rvDestination.layoutManager = GridLayoutManager(this, 2)
                    showRecyclerGrid()
                    Toast.makeText(this, "Grid View Mode", Toast.LENGTH_SHORT).show()
                    item.setIcon(R.drawable.icon_list)
                } else {
//                    rvDestination.layoutManager = LinearLayoutManager(this)
                    showRecyclerList()
                    Toast.makeText(this, "List View Mode", Toast.LENGTH_SHORT).show()
                    item.setIcon(R.drawable.icon_grid)
                    showingMode = false
                }

            }
            R.id.about_page -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}