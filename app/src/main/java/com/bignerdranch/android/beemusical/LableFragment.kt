package com.bignerdranch.android.beemusical

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class LableFragment:Fragment() {

    private lateinit var startImageButton: ImageButton
    private lateinit var hostActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Передача View
        val view = inflater.inflate(R.layout.fragment_lable, container, false)

        //Инициализация виджетов
        startImageButton = view.findViewById(R.id.start_image_button) as ImageButton

        return view
    }

    override fun onStart() {
        super.onStart()

        //Добавление слушателя на кнопку
        startImageButton.setOnClickListener{
            hostActivity.onFragmentSelected(DescriptionFragment.newInstance())
        }
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
        fun newInstance(): LableFragment{
            return LableFragment()
        }
    }
}