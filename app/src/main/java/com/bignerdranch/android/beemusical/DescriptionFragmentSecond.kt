package com.bignerdranch.android.beemusical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class DescriptionFragmentSecond: Fragment() {

    private lateinit var nextImageButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_description_2, container, false)

        nextImageButton = view.findViewById(R.id.continue_image_button) as ImageButton

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object{
        fun newInstance(): DescriptionFragmentSecond{
            return DescriptionFragmentSecond()
        }
    }
}