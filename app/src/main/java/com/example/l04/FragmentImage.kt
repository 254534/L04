package com.example.l04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

private const val ARG_PARAM1 = "param1"

class FragmentImage : Fragment() {
    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView: ImageView = view.findViewById(R.id.imageViewNoContent)
        imageView.setImageResource(imageArr[param1!!])

        imageView.setOnClickListener {
            (parentFragment as Fragment3).setCurrentImage()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    companion object {
        val imageArr: IntArray = intArrayOf(
            R.drawable.z1,
            R.drawable.z2,
            R.drawable.z3,
            R.drawable.z4)

        @JvmStatic fun newInstance(param1: Int) =
            FragmentImage().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}