package com.example.l04

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Fragment1 : Fragment() {
    companion object {
        var currentPhoto: Int = 0
        var imageArr: IntArray = FragmentImage.imageArr
        lateinit var preferences: SharedPreferences
        const val SHARED_PHOTO = "currentPhoto"
        const val SHARED_PHOTO_INX = "current"
        var tempColor: Int? = null
        var textContent: String = ""
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

        var preferencesText = requireContext().getSharedPreferences(Fragment22.SHARED_TEXT, Context.MODE_PRIVATE)
        val text: TextView = view.findViewById(R.id.textView2)
        text.text = Editable.Factory.getInstance().newEditable(
            preferencesText.getString(
                Fragment22.SHARED_TEXT_CONTENT, "couldn't load text"))

        var preferencesLabel = requireContext().getSharedPreferences(Fragment23.SHARED_TITLE, Context.MODE_PRIVATE)
        val label: TextView = view.findViewById(R.id.textView)
        if (preferencesLabel.getBoolean(Fragment23.SHARED_TITLE_BOLD, false)) {
            label.setTypeface(label.typeface, Typeface.BOLD);
        }
        if (preferencesLabel.getBoolean(Fragment23.SHARED_TITLE_ITALIC, false)) {
            label.setTypeface(label.typeface, Typeface.ITALIC);
        }
        if (preferencesLabel.getBoolean(Fragment23.SHARED_TITLE_BOLD, false) and
            preferencesLabel.getBoolean(Fragment23.SHARED_TITLE_ITALIC, false)) {
            label.setTypeface(label.typeface, Typeface.BOLD_ITALIC);
        }

        parentFragmentManager.setFragmentResultListener("photoChange", viewLifecycleOwner) {
            requestKey, bundle ->
            currentPhoto = bundle.getInt(SHARED_PHOTO_INX, 0)
            preferences.edit().putInt(SHARED_PHOTO_INX, currentPhoto).apply()
            updatePhoto(view)
        }

        val currentPhotoId: Int = currentPhoto
        var buldleVar: Bundle = Bundle()
        buldleVar.putInt("current", currentPhotoId)
        parentFragmentManager.setFragmentResult("photoInit", buldleVar)

        parentFragmentManager.setFragmentResultListener("hexBackground", viewLifecycleOwner) {
                requestKey, bundle ->
            var hexArray: IntArray = bundle.getIntArray("hexTab")!!
            val newColor: Int = Color.parseColor(
                "#${Fragment21.newHexColor(255 - hexArray[0])}${
                    Fragment21.newHexColor(
                        255 - hexArray[1]
                    )
                }${Fragment21.newHexColor(255 - hexArray[2])}")
            tempColor = newColor
            view.setBackgroundColor(tempColor!!)
        }

        parentFragmentManager.setFragmentResultListener("currentText", viewLifecycleOwner) {
                requestKey, bundle ->
            textContent = bundle.getString("content", "")
            text.text = Editable.Factory.getInstance().newEditable(textContent)
//            Toast.makeText(requireContext(), "x", Toast.LENGTH_LONG).show()
        }

        if(tempColor != null) {
            view.setBackgroundColor(tempColor!!)
        }
        text.text = Editable.Factory.getInstance().newEditable(textContent)

        val button: Button = view.findViewById(R.id.buttonSavePreferences)
        button.setOnClickListener {
            Fragment21.preferences.edit().putInt(Fragment21.SHARED_BACKGROUND_RED, Fragment21.hexArray[0]).apply()
            Fragment21.preferences.edit().putInt(Fragment21.SHARED_BACKGROUND_GREEN, Fragment21.hexArray[1]).apply()
            Fragment21.preferences.edit().putInt(Fragment21.SHARED_BACKGROUND_BLUE, Fragment21.hexArray[2]).apply()

            requireContext().getSharedPreferences(Fragment22.SHARED_TEXT, Context.MODE_PRIVATE).edit().putString(
                Fragment22.SHARED_TEXT_CONTENT, textContent).apply()

            view.rootView.setBackgroundColor(newColor)
        }
    }
}