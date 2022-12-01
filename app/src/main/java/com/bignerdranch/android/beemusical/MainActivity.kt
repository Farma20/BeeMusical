package com.bignerdranch.android.beemusical

import android.media.MediaPlayer
import android.media.MediaPlayer.OnBufferingUpdateListener
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.*

class MainActivity : AppCompatActivity() {

    //создание плеера
    private lateinit var player:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Убираем верхнюю панель с названием
        supportActionBar?.hide()

        //Создание музыкального плеера
        player = MediaPlayer.create(this, R.raw.backgroung_music)

        //Создание слушателя для MediaPlayer
        val playerListener = OnCompletionListener {
            player.start()
        }

        player.setOnCompletionListener(playerListener)
        player.start()




        //Транзакция фрагмента
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null){
            val fragment = LabelFragment.newInstance()

            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }

    private fun playMedia(){

    }


    fun onFragmentSelected(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}