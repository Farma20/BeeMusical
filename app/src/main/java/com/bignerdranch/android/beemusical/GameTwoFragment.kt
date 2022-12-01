package com.bignerdranch.android.beemusical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

class GameTwoFragment: Fragment() {

    //создание кнопок
    private lateinit var noteDo: ImageView
    private lateinit var noteRe: ImageView
    private lateinit var noteMi: ImageView
    private lateinit var noteFa: ImageView
    private lateinit var noteSol: ImageView
    private lateinit var noteLa: ImageView
    private lateinit var noteSi: ImageView
    private lateinit var noteDoLast: ImageView

    //инициализация списков
    private lateinit var noteList: List<ImageView>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_two, container, false)

        //инициализация кнопок
        noteDo = view.findViewById(R.id.two_game_do)
        noteRe = view.findViewById(R.id.two_game_re)
        noteMi = view.findViewById(R.id.two_game_mi)
        noteFa = view.findViewById(R.id.two_game_fa)
        noteSol = view.findViewById(R.id.two_game_sol)
        noteLa = view.findViewById(R.id.two_game_la)
        noteSi = view.findViewById(R.id.two_game_si)
        noteDoLast = view.findViewById(R.id.two_game_do2)

        //инициализация списков
        noteList = listOf(noteDo, noteRe, noteMi, noteFa, noteSol, noteLa, noteSi, noteDoLast)

        return view
    }

    override fun onStart() {
        super.onStart()

        for (i in noteList.indices){
            noteList[i].setOnClickListener{
                if(it.alpha == 0.0f){
                    it.alpha = 1.0f
                }
                else{
                    it.alpha = 0.0f
                }
            }

            println(noteList[i].isVisible)
        }

    }

    companion object{
        fun newInstance():GameTwoFragment{
            return GameTwoFragment()
        }
    }

}