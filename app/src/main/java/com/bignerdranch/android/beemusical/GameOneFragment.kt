package com.bignerdranch.android.beemusical

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import android.speech.tts.TextToSpeech
import java.util.*

class GameOneFragment: Fragment() {
    private lateinit var hostActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_one, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()

        hostActivity.speak()

    }



    //функция, отлавливающая хост-активити
    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()

    }

    companion object{
        fun newInstance(): GameOneFragment{
            return GameOneFragment()
        }
    }
}