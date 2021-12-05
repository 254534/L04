package com.example.l04

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast

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
        var hexArray: Array<String> = arrayOf("ff", "ff", "ff")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val seekBarRed: SeekBar = view.findViewById(R.id.seekBarRed)
        val seekBarGreen: SeekBar = view.findViewById(R.id.seekBarGreen)
        val seekBarBlue: SeekBar = view.findViewById(R.id.seekBarBlue)
        seekBarRed.setOnSeekBarChangeListener(seekBarListenerRed)
        seekBarGreen.setOnSeekBarChangeListener(seekBarListenerGreen)
        seekBarBlue.setOnSeekBarChangeListener(seekBarListenerBlue)
    }

    fun setNewBackgroundColor() {
        val newColor: Int = Color.parseColor("#${hexArray.joinToString("")}")
        requireView().setBackgroundColor(newColor)
    }

    fun newHexColor(i: Int): String {
        val newI = 255-(i*255)/100
        return Integer.toHexString(newI).padStart(2, '0')
    }

    val seekBarListenerRed = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            hexArray[0] = newHexColor(i)
            setNewBackgroundColor()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            // Do something
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            // Do something
        }
    }

    val seekBarListenerGreen = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            hexArray[1] = newHexColor(i)
            setNewBackgroundColor()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            // Do something
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            // Do something
        }
    }

    val seekBarListenerBlue = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            hexArray[2] = newHexColor(i)
            setNewBackgroundColor()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            // Do something
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            // Do something
        }
    }
}