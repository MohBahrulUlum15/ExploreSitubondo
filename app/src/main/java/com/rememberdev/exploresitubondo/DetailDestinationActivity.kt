package com.rememberdev.exploresitubondo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class DetailDestinationActivity : AppCompatActivity() {

    private lateinit var nameContentShare: String
    private lateinit var descriptionContentShare: String

    companion object {
        const val EXTRA_DESTINATION = "extra_destination"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_destination)

        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvLocation: TextView = findViewById(R.id.tv_location)
        val tvOpen: TextView = findViewById(R.id.tv_open)
        val tvTicket: TextView = findViewById(R.id.tv_ticket)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        val imgThumbnail: ImageView = findViewById(R.id.img_thumbnail)
        val imgPhoto1: ImageView = findViewById(R.id.img_photo1)
        val imgPhoto2: ImageView = findViewById(R.id.img_photo2)
        val imgPhoto3: ImageView = findViewById(R.id.img_photo3)
        val btnShare: Button = findViewById(R.id.btn_share)

        val destination = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DESTINATION, Destination::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DESTINATION)
        }

        if (destination != null) {
            tvName.text = destination.name.toString()
            tvLocation.text = destination.location.toString()
            tvOpen.text = destination.open.toString()
            tvTicket.text = destination.ticket.toString()
            tvDescription.text = destination.description.toString()
            destination.photo1?.let { imgThumbnail.setImageResource(it) }
            destination.photo2?.let { imgPhoto1.setImageResource(it) }
            destination.photo3?.let { imgPhoto2.setImageResource(it) }
            destination.photo1?.let { imgPhoto3.setImageResource(it) }

            nameContentShare = destination.name.toString()
            descriptionContentShare = destination.description.toString()

            btnShare.setOnClickListener{
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "${destination.name} \n \n${destination.description}"
                    )
                    type = "text/plain"
                }
                startActivity(shareIntent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "${nameContentShare} \n \n${descriptionContentShare}"
                    )
                    type = "text/plain"
                }
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}