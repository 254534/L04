package com.example.l04

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

private const val SHARED_PHOTO = "currentPhoto"
private const val SHARED_PHOTO_INX = "current"

class Fragment1 : Fragment() {
    companion object {
        var currentPhoto: Int = 0
        var imageArr: IntArray = FragmentImage.imageArr
        lateinit var preferences: SharedPreferences
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    fun updatePhoto(view: View) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        imageView.setImageResource(imageArr[currentPhoto])
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = requireContext().getSharedPreferences(SHARED_PHOTO, Context.MODE_PRIVATE)
        currentPhoto = preferences.getInt(SHARED_PHOTO_INX, 0)
        updatePhoto(view)

        var preferencesColor: SharedPreferences = requireContext().getSharedPreferences(Fragment21.SHARED_BACKGROUND, Context.MODE_PRIVATE)
        var red = preferencesColor.getInt(Fragment21.SHARED_BACKGROUND_RED, 0)
        var green = preferencesColor.getInt(Fragment21.SHARED_BACKGROUND_GREEN, 0)
        var blue = preferencesColor.getInt(Fragment21.SHARED_BACKGROUND_BLUE, 0)

        val newColor: Int = Color.parseColor(
            "#${Fragment21.newHexColor(255- red)}${Fragment21.newHexColor(255- green)}${Fragment21.newHexColor(255- blue)}")

        view.rootView.setBackgroundColor(newColor)

        parentFragmentManager.setFragmentResultListener("photoChange", viewLifecycleOwner) {
            requestKey, bundle ->
            currentPhoto = bundle.getInt(SHARED_PHOTO_INX, 0)
            preferences.edit().putInt(SHARED_PHOTO_INX, currentPhoto).apply()
            updatePhoto(view)
        }
    }
}