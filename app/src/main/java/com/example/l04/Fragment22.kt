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
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment22.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment22 : Fragment() {
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
        return inflater.inflate(R.layout.fragment_22, container, false)
    }


    companion object {
        const val SHARED_TEXT = "currentText"
        const val SHARED_TEXT_CONTENT = "content"
        lateinit var preferences: SharedPreferences

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment22().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = requireContext().getSharedPreferences(SHARED_TEXT, Context.MODE_PRIVATE)
        val editText: EditText = view.findViewById(R.id.editText)
        editText.text = Editable.Factory.getInstance().newEditable(preferences.getString(SHARED_TEXT_CONTENT, "couldn't load text"))

        view.findViewById<Button>(R.id.buttonSetText).setOnClickListener {
            preferences.edit().putString(SHARED_TEXT_CONTENT, editText.text.toString()).apply()
        }
    }
}