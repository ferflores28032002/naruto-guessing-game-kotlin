package com.example.game_guess

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val regresarSecondScreenButton: Button = findViewById(R.id.regresarFirstScreenButton)
        val regresarFirstScreenButton: Button = findViewById(R.id.regresarFirstScreenButton)

        // Inicializa MediaPlayer con el archivo de audio deseado
        mediaPlayer = MediaPlayer.create(this, R.raw.success)

        // Inicia el audio
        mediaPlayer.start()

        regresarSecondScreenButton.setOnClickListener {
            val intent = Intent(this, secondScreen::class.java)
            startActivity(intent)
        }

        regresarFirstScreenButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        // Libera el recurso de MediaPlayer cuando la Activity se destruye
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
        super.onDestroy()
    }
}
