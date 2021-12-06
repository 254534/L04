package com.example.l04

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


class Fragment21 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_21, container, false)
    }

    companion object {
        const val SHARED_BACKGROUND = "currentBackground"
        const val SHARED_BACKGROUND_RED = "currentRed"
        const val SHARED_BACKGROUND_GREEN = "currentGreen"
        const val SHARED_BACKGROUND_BLUE = "currentBlue"
        var hexArray: IntArray = intArrayOf(255, 255, 255)
        lateinit var preferences: SharedPreferences

        fun newHexColor(i: Int): String {
//        val newI = (i*255)/100
            return Integer.toHexString(i).padStart(2, '0')
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val seekBarRed: SeekBar = view.findViewById(R.id.seekBarRed)
        val seekBarGreen: SeekBar = view.findViewById(R.id.seekBarGreen)
        val seekBarBlue: SeekBar = view.findViewById(R.id.seekBarBlue)

        seekBarRed.max = 255
        seekBarGreen.max = 255
        seekBarBlue.max = 255

        seekBarRed.setOnSeekBarChangeListener(seekBarListenerRed)
        seekBarGreen.setOnSeekBarChangeListener(seekBarListenerGreen)
        seekBarBlue.setOnSeekBarChangeListener(seekBarListenerBlue)

        preferences = requireContext().getSharedPreferences(SHARED_BACKGROUND, Context.MODE_PRIVATE)
        hexArray[0] = preferences.getInt(SHARED_BACKGROUND_RED, 0)
        hexArray[1] = preferences.getInt(SHARED_BACKGROUND_GREEN, 0)
        hexArray[2] = preferences.getInt(SHARED_BACKGROUND_BLUE, 0)

        seekBarRed.setProgress(hexArray[0], true)
        seekBarGreen.setProgress(hexArray[1], true)
        seekBarBlue.setProgress(hexArray[2], true)

        view.findViewById<Button>(R.id.buttonSetColor).setOnClickListener {
            val newColor: Int = Color.parseColor(
                "#${newHexColor(255- hexArray[0])}${newHexColor(255- hexArray[1])}${newHexColor(255- hexArray[2])}")

            Toast.makeText(requireContext(), "#${newHexColor(255- hexArray[0])}${newHexColor(255- hexArray[1])}${newHexColor(255- hexArray[2])}", Toast.LENGTH_SHORT).show()
            preferences.edit().putInt(SHARED_BACKGROUND_RED, hexArray[0]).apply()
            preferences.edit().putInt(SHARED_BACKGROUND_GREEN, hexArray[1]).apply()
            preferences.edit().putInt(SHARED_BACKGROUND_BLUE, hexArray[2]).apply()

            view.rootView.setBackgroundColor(newColor)
        }
    }

    fun setNewBackgroundColor() {
        val newColor: Int = Color.parseColor(
            "#${newHexColor(255- hexArray[0])}${newHexColor(255- hexArray[1])}${newHexColor(255- hexArray[2])}")
        requireView().setBackgroundColor(newColor)
    }

    val seekBarListenerRed = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            hexArray[0] = i
            setNewBackgroundColor()
            var buldleVar: Bundle = Bundle()
            buldleVar.putIntArray("hexTab", hexArray)
            parentFragmentManager.setFragmentResult("hexBackground", buldleVar)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    val seekBarListenerGreen = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            hexArray[1] = i
            setNewBackgroundColor()
            var buldleVar: Bundle = Bundle()
            buldleVar.putIntArray("hexTab", hexArray)
            parentFragmentManager.setFragmentResult("hexBackground", buldleVar)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    val seekBarListenerBlue = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            hexArray[2] = i
            setNewBackgroundColor()
            var buldleVar: Bundle = Bundle()
            buldleVar.putIntArray("hexTab", hexArray)
            parentFragmentManager.setFragmentResult("hexBackground", buldleVar)
            //Toast.makeText(requireContext(), "x", Toast.LENGTH_LONG).show()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }
}