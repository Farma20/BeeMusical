package com.bignerdranch.android.beemusical

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class DescriptionFragmentFirst: Fragment() {

    private lateinit var nextImageButton: ImageButton
    private lateinit var hostActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_description_1, container, false)

        nextImageButton = view.findViewById(R.id.continue_image_button) as ImageButton

        return view
    }

    override fun onStart() {
        super.onStart()

        nextImageButton.setOnClickListener{
            hostActivity.onFragmentSelected(DescriptionFragmentSecond.newInstance())
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
        fun newInstance(): DescriptionFragmentFirst{
            return DescriptionFragmentFirst()
        }
    }
}