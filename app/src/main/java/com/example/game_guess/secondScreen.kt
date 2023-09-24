package com.example.game_guess

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class secondScreen : AppCompatActivity() {

    private var intentosRestantes = 7
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)


        val imagenesPersonajes = mapOf(
            R.drawable.itachi to "Itachi",
            R.drawable.jirayajpeg to "Jiraya",
            R.drawable.naruto to "Naruto",
            R.drawable.minato to "minato",
            R.drawable.sakura to "sakura",
            R.drawable.sasuque to "sasuke",
            R.drawable.oro to "orochimaru",
            R.drawable.shisune to "shisune",
        )

        val personajeImageView: ImageView = findViewById(R.id.personajeImageView)
        val nombrePersonajeEditText: EditText = findViewById(R.id.nombrePersonajeEditText)
        val verificarButton: Button = findViewById(R.id.verificarButton)
        val intentosTextView: TextView = findViewById(R.id.intentosTextView)

        val (idImagenAleatoria, nombrePersonaje) = imagenesPersonajes.entries.random()
        personajeImageView.setImageResource(idImagenAleatoria)

        verificarButton.setOnClickListener {
            val respuestaUsuario = nombrePersonajeEditText.text.toString().trim()
            nombrePersonajeEditText.text.clear()

            if (respuestaUsuario.equals(nombrePersonaje, ignoreCase = true)) {
                val intent = Intent(this, SuccessActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                intentosRestantes--
                intentosTextView.text = "Intentos: $intentosRestantes"
                if (intentosRestantes <= 0) {
                    val intent = Intent(this, ErrorMessageActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "¡Incorrecto! Intentos restantes: $intentosRestantes", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Inicializa y reproduce el audio al entrar a la pantalla
        mediaPlayer = MediaPlayer.create(this, R.raw.pelea)
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()

        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }
}
