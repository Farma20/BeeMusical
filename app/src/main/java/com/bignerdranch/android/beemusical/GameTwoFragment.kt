package com.bignerdranch.android.beemusical

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import java.lang.Thread.sleep
import java.util.*
import kotlin.concurrent.thread

class GameTwoFragment: Fragment() {

    lateinit var mTTs: TextToSpeech

    private lateinit var hostActivity: MainActivity

    private lateinit var congrat: ImageView

    //создание кнопок
    private lateinit var noteDo: ImageView
    private lateinit var noteRe: ImageView
    private lateinit var noteMi: ImageView
    private lateinit var noteFa: ImageView
    private lateinit var noteSol: ImageView
    private lateinit var noteLa: ImageView
    private lateinit var noteSi: ImageView
    private lateinit var noteDoLast: ImageView

    //создание ViewHolder
    private lateinit var imageViewHolder: ImageView

    //инициализация списков
    private lateinit var noteList: List<ImageView>
    private lateinit var notes: List<String>
    private lateinit var notes2: List<String>
    private lateinit var noteImageViewList: List<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_two, container, false)

        congrat = view.findViewById(R.id.congras2)

        //инициализация кнопок
        noteDo = view.findViewById(R.id.two_game_do)
        noteRe = view.findViewById(R.id.two_game_re)
        noteMi = view.findViewById(R.id.two_game_mi)
        noteFa = view.findViewById(R.id.two_game_fa)
        noteSol = view.findViewById(R.id.two_game_sol)
        noteLa = view.findViewById(R.id.two_game_la)
        noteSi = view.findViewById(R.id.two_game_si)
        noteDoLast = view.findViewById(R.id.two_game_do2)

        //инициализация imageViewHolder
        imageViewHolder = view.findViewById(R.id.imageViewHolder)

        //инициализация списков
        noteList = listOf(noteDo, noteRe, noteMi, noteFa, noteSol, noteLa, noteSi, noteDoLast)

        notes = listOf("ДОМ", "РЕПКУ", "МИШКУ", "ФАКЕЛ", "СОЛНЦЕ", "ЛЯГУШКУ",
            "СИНИЦУ", "ДОМ")

        notes2 = listOf("ДОМ", "РЕПКА", "МИШКА", "ФАКЕЛ", "СОЛНЦЕ", "ЛЯГУШКА",
            "СИНИЦА", "ДОМ")

        noteImageViewList = listOf(R.drawable.game_two_do_rect, R.drawable.game_two_re_rect,
            R.drawable.game_two_mi_rect, R.drawable.game_two_fa_rect, R.drawable.game_two_sol_rect,
            R.drawable.game_two_la_rect, R.drawable.game_two_si_rect, R.drawable.game_two_do_rect)

        return view
    }

    override fun onStart() {
        super.onStart()

        var currentValue: Int = 0

        //Инициализация tts через контекст и onInit ассинфхронную функцию
        thread {
            mTTs = TextToSpeech(this.context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    val locale = Locale("RU")
                    mTTs.language = locale
                    currentValue = 0
                    speak("Покажи мне ${notes[currentValue]}")
                }
            }
        }

        congrat.setOnClickListener{
            hostActivity.onFragmentSelected(MenuFragment.newInstance(3))
        }

        for (i in noteList.indices){
            noteList[i].setOnClickListener{
                if(it.alpha == 0.0f){

                    //Включаем кнопку, которая была включена
                    for(j in noteList.indices)
                        noteList[j].alpha = 0.0f

                    //Выключаем нажатую кнопку
                    it.alpha = 1.0f

                    //Передаем изображение нажатой кнопки
                    imageViewHolder.setImageResource(noteImageViewList[i])
                }

                if(currentValue == i){
                    currentValue++
                    if(currentValue < noteList.size)
                        speak("Верно! Теперь покажи мне ${notes[currentValue]}")
                    else{
                        speak("Верно!")
                        congrat.visibility = View.VISIBLE
                    }

                }
                else{
                    speak("Неверно, это ${notes2[i]}! Попробуй еще раз. Покажи мне ${notes[currentValue]}")
                }

            }
        }

    }

    //функция активирующая произношение слов
    private fun speak(str:String){
        val text: String = str
        val map = HashMap<String, String>()
        map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "UniqueID"
        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH, map)
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
        fun newInstance():GameTwoFragment{
            return GameTwoFragment()
        }
    }

}