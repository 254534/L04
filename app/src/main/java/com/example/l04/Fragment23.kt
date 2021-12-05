package com.example.l04

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment23.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment23 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_23, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = requireContext().getSharedPreferences(SHARED_TITLE, Context.MODE_PRIVATE)
        val boldSwitch: Switch = view.findViewById(R.id.switchBold)
        boldSwitch.isChecked = preferences.getBoolean(SHARED_TITLE_BOLD, false)
        boldSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            preferences.edit().putBoolean(SHARED_TITLE_BOLD, isChecked).apply()
        }

        val italicSwitch: Switch = view.findViewById(R.id.switchItalic)
        italicSwitch.isChecked = preferences.getBoolean(SHARED_TITLE_ITALIC, false)
        italicSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            preferences.edit().putBoolean(SHARED_TITLE_ITALIC, isChecked).apply()
        }
    }

    companion object {
        const val SHARED_TITLE = "currentFontAdjustments"
        const val SHARED_TITLE_ITALIC = "italic"
        const val SHARED_TITLE_BOLD = "bold"
        lateinit var preferences: SharedPreferences

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment23().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}