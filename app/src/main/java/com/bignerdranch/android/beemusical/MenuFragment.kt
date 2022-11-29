package com.bignerdranch.android.beemusical

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class MenuFragment: Fragment() {

    private lateinit var hostActivity: MainActivity
    private lateinit var gameOneImageButton: ImageButton
    private lateinit var gameTwoImageButton: ImageButton
    private lateinit var gameThreeImageButton: ImageButton
    private lateinit var gameFourImageButton: ImageButton
    private lateinit var gameFiveImageButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        gameOneImageButton = view.findViewById(R.id.game_one_image_button) as ImageButton
        gameTwoImageButton = view.findViewById(R.id.game_two_image_button) as ImageButton
        gameThreeImageButton = view.findViewById(R.id.game_three_image_button) as ImageButton
        gameFourImageButton = view.findViewById(R.id.game_four_image_button) as ImageButton
        gameFiveImageButton = view.findViewById(R.id.game_five_image_button) as ImageButton

        return view
    }

    override fun onStart() {
        super.onStart()

        gameOneImageButton.setOnClickListener{
            hostActivity.onFragmentSelected(GameOneFragment.newInstance())
        }

        gameTwoImageButton.setOnClickListener{
            hostActivity.onFragmentSelected(GameTwoFragment.newInstance())
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
        fun newInstance(): MenuFragment{
            return MenuFragment()
        }
    }
}