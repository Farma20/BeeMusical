package com.bignerdranch.android.beemusical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class GameTwoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_two, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()
    }

    companion object{
        fun newInstance():GameTwoFragment{
            return GameTwoFragment()
        }
    }

}