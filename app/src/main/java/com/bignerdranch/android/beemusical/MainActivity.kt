package com.bignerdranch.android.beemusical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mTTs: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        mTTs = TextToSpeech(this){status->
            if(status == TextToSpeech.SUCCESS){
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

    fun speak(){
        val text: String = "Стоит на поле домик. Рядом растет репка. Вечером приходит мишка" +
                "зажигает факел и ложится спать. А утром выглядывает солнышко. Квакают ля гушки. "+
                "и синички летят в свой маленький домик"
        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH, null)
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