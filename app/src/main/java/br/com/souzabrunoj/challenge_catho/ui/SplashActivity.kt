package br.com.souzabrunoj.challenge_catho.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.souzabrunoj.challenge_catho.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startHome()
        }, 2000L)
    }

    private fun startHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        finish()
    }
}