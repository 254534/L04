package com.example.l04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

class Fragment1 : Fragment() {
    companion object {
        var currentPhoto: Int = 0
        var imageArr: IntArray = FragmentImage.imageArr
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
        updatePhoto(view)
        parentFragmentManager.setFragmentResultListener("photoChange", viewLifecycleOwner) {
            requestKey, bundle ->
            currentPhoto = bundle.getInt("current", 0)
            updatePhoto(view)
        }
    }
}