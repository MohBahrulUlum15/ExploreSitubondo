package com.rememberdev.exploresitubondo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    private val urlInstagram = "https://www.instagram.com/ulumbahrul_15/"
    private val urlLinkedin = "https://www.linkedin.com/in/moh-bahrul-ulum-ab52721b7/"
    private val urlGithub = "https://github.com/MohBahrulUlum15/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val actionbar = supportActionBar
        actionbar!!.title = "Profile"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val tvInstagram: TextView = findViewById(R.id.tv_instagram)
        val tvLinkedin: TextView = findViewById(R.id.tv_linkedin)
        val tvGithub: TextView = findViewById(R.id.tv_github)

        tvInstagram.setOnClickListener(this)
        tvLinkedin.setOnClickListener(this)
        tvGithub.setOnClickListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_instagram -> {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(urlInstagram)})
            }
            R.id.tv_linkedin -> {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(urlLinkedin)})
            }
            R.id.tv_github -> {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(urlGithub)})
            }
        }
    }
}