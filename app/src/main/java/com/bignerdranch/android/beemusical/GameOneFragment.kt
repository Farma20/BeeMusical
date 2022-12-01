package com.bignerdranch.android.beemusical

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.lang.Thread.sleep
import java.util.*


class GameOneFragment: Fragment() {
    private lateinit var hostActivity: MainActivity

    //Создание ппеременной, хранящей класс с генератором озвучивания текста
    lateinit var mTTs: TextToSpeech

    private lateinit var gameBackgroundImageView: ImageView
    private lateinit var noteDo: ImageView
    private lateinit var noteRe: ImageView
    private lateinit var noteMi: ImageView
    private lateinit var noteFa: ImageView
    private lateinit var noteSol: ImageView
    private lateinit var noteLa: ImageView
    private lateinit var noteSi: ImageView
    private lateinit var noteDoLast: ImageView
    private lateinit var listImageView: List<ImageView>
    private lateinit var congras:ImageView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_one, container, false)

        gameBackgroundImageView = view.findViewById(R.id.background)
        noteDo = view.findViewById(R.id.one_game_DO)
        noteRe = view.findViewById(R.id.one_game_RE)
        noteMi = view.findViewById(R.id.one_game_MI)
        noteFa = view.findViewById(R.id.one_game_FA)
        noteSol = view.findViewById(R.id.one_game_SOL)
        noteLa = view.findViewById(R.id.one_game_LA)
        noteSi = view.findViewById(R.id.one_game_SI)
        noteDoLast = view.findViewById(R.id.one_game_DO_last)

        listImageView = listOf(noteDo, noteRe, noteMi, noteFa, noteSol, noteLa, noteSi, noteDoLast)

        congras = view.findViewById(R.id.congras)



        return view
    }

    //функция активирующая произношение слов
    private fun speak(str:String){
        val text: String = str
        val map = HashMap<String, String>()
        map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "UniqueID"
        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH, map)
    }


    private fun gamePlay(notes:List<String>, viewOriginal:List<Int>, viewSelect:List<Int>){
        for(i in notes.indices){
            listImageView[i].setImageResource(viewSelect[i])
            speak("Скажи ${notes[i]}")
            sleep(2000)
//            listImageView[i].setImageResource(viewOriginal[i])
        }
    }



    override fun onStart() {
        super.onStart()

        //Инициалиация объектов взаимодействия с View
        val notes: List<String> = listOf("ДОМ", "РЕПКА", "МИШКА", "ФАКЕЛ", "СОЛНЦЕ", "ЛЯГУШКА",
            "СИНИЦА", "ДОМ")

        val viewOriginal: List<Int> = listOf(R.drawable.one_game_do, R.drawable.one_game_re,
            R.drawable.one_game_mi, R.drawable.one_game_fa, R.drawable.one_game_sol,
            R.drawable.one_game_la, R.drawable.one_game_si, R.drawable.one_game_do)

        val viewSelect: List<Int> = listOf(R.drawable.one_game_do_select, R.drawable.one_game_re_select,
            R.drawable.one_game_mi_select, R.drawable.one_game_fa_select,
            R.drawable.one_game_sol_select, R.drawable.one_game_la_select,
            R.drawable.one_game_si_select, R.drawable.one_game_do_select)

        var i = 0

        //Слушатель tts, который отслеживает начало и завершение произношения
        val speechListener = object : UtteranceProgressListener(){
            override fun onStart(utteranceId: String?) {
                listImageView[i].setImageResource(viewSelect[i])
                i++
            }

            override fun onDone(utteranceId: String?) {
                if(i == listImageView.size){
                    listImageView[i-1].setImageResource(viewOriginal[i-1])
                    congras.alpha = 1.0f
                    sleep(2000)

                }else{
                    speak("Скажи ${notes[i]}")
                    gameBackgroundImageView.setImageResource(R.drawable.one_game_background_lourge)
                    listImageView[i-1].setImageResource(viewOriginal[i-1])
                }
            }

            @Deprecated("Deprecated in Java", ReplaceWith("println(\"TTS ERROR\")"))
            override fun onError(utteranceId: String?) {
                println("TTS ERROR")
            }
        }

        //Инициализация tts через контекст и onInit ассинфхронную функцию
        mTTs = TextToSpeech(this.context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val locale = Locale("RU")
                mTTs.language = locale
                mTTs.setOnUtteranceProgressListener(speechListener)
                speak("Стоит на поле домик. Рядом растет репка. Вечером приходит мишка, " +
                        "зажигает факел и ложится спать. А утром выглядывает солнышко. Квакают ля гушки." +
                        " и синички летят в свой маленький домик")
            }
        }



    }


    override fun onDestroy() {
        super.onDestroy()


    }

    //функция, отлавливающая хост-активити
    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        mTTs.stop()
        mTTs.shutdown()
    }

    companion object{
        fun newInstance(): GameOneFragment{
            return GameOneFragment()
        }
    }
}