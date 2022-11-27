package com.bignerdranch.android.beemusical

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mTTs: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        mTTs = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val locale: Locale = Locale("RU")
                val result = mTTs.setLanguage(locale)
            }

        }

        //Транзакция фрагмента
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null){
            val fragment = LabelFragment.newInstance()

            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }

    fun speak(text_:String){
        val text: String = text_

        val map = HashMap<String, String>()
        map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "UniqueID";
        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH, map)
    }

    fun onFragmentSelected(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mTTs != null){
            mTTs.stop()
            mTTs.shutdown()
        }
    }


}