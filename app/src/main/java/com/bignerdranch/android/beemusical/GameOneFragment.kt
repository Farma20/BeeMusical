package com.bignerdranch.android.beemusical

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.ImageView
import java.util.*

class GameOneFragment: Fragment() {
    private lateinit var hostActivity: MainActivity
    private lateinit var gameBackgroundImageView: ImageView
    private lateinit var DO: ImageView
    private lateinit var RE: ImageView
    private lateinit var MI: ImageView
    private lateinit var FA: ImageView
    private lateinit var SOL: ImageView
    private lateinit var LA: ImageView
    private lateinit var SI: ImageView
    private lateinit var DO_last: ImageView
    private lateinit var listImageView: List<ImageView>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_one, container, false)

        gameBackgroundImageView = view.findViewById(R.id.background)
        DO = view.findViewById(R.id.one_game_DO)
        RE = view.findViewById(R.id.one_game_RE)
        MI = view.findViewById(R.id.one_game_MI)
        FA = view.findViewById(R.id.one_game_FA)
        SOL = view.findViewById(R.id.one_game_SOL)
        LA = view.findViewById(R.id.one_game_LA)
        SI = view.findViewById(R.id.one_game_SI)
        DO_last = view.findViewById(R.id.one_game_DO_last)

        listImageView = listOf(DO, RE, MI, FA, SOL, LA, SI, DO_last)

        return view
    }

    override fun onStart() {
        super.onStart()

        val notes: List<String> = listOf("ДОМ","РЕПКА","МИШКА","ФАКЕЛ","СОЛНЦЕ", "ЛЯГУШКА", "СИНИЦА","ДОМ",)

        val res_orig: List<Int> = listOf(R.drawable.one_game_do, R.drawable.one_game_re,
            R.drawable.one_game_mi, R.drawable.one_game_fa, R.drawable.one_game_sol,
            R.drawable.one_game_la, R.drawable.one_game_si, R.drawable.one_game_do)

        val res_sel: List<Int> = listOf(R.drawable.one_game_do_select, R.drawable.one_game_re_select,
            R.drawable.one_game_mi_select, R.drawable.one_game_fa_select,
            R.drawable.one_game_sol_select, R.drawable.one_game_la_select,
            R.drawable.one_game_si_select, R.drawable.one_game_do_select)

        var j = 0

        val speechListener1 = object : UtteranceProgressListener(){
            override fun onStart(utteranceId: String?) {
                listImageView[j].setImageResource(res_sel[j])
                j++

            }

            override fun onDone(utteranceId: String?) {
                gameBackgroundImageView.setImageResource(R.drawable.one_game_background_lourge)
                listImageView[j].setImageResource(res_orig[j])
                hostActivity.speak("Скажи ${notes[j]}")

            }

            override fun onError(utteranceId: String?) {
            }
        }



        hostActivity.mTTs.setOnUtteranceProgressListener(speechListener1)


        hostActivity.speak("Стоит на поле домик.Рядом растет репка. Вечером приходит мишка" +
                "зажигает факел и ложится спать. А утром выглядывает солнышко. Квакают ля гушки." +
                "и синички летят в свой маленький домик")




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