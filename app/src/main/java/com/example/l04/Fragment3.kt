package com.example.l04

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlin.properties.Delegates

class Fragment3 : Fragment() {
    companion object {
        var currentPhotoInx: Int = 0
        lateinit var vPager: ViewPager2
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    class MySwipeAdapter(fg: Fragment): FragmentStateAdapter(fg) {
        override fun createFragment(position: Int): Fragment {
            return FragmentImage.newInstance(position)
        }
        override fun getItemCount(): Int {
            return 4
        }

//    override fun getPageTitle(position: Int): CharSequence? {
//        return
//    }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vpAdapter = MySwipeAdapter(this)

        vPager = view.findViewById<ViewPager2>(R.id.vpager2Swipe)
        vPager.adapter = vpAdapter

        parentFragmentManager.setFragmentResultListener("photoInit", viewLifecycleOwner) {
                requestKey, bundle ->
            currentPhotoInx = bundle.getInt(Fragment1.SHARED_PHOTO_INX, 0)
        }

        val imageChosen: ImageView = view.findViewById(R.id.imageViewChosenInF3)
        imageChosen.setImageResource(FragmentImage.imageArr[currentPhotoInx])

        val button: Button = view.findViewById(R.id.buttonChoosePicture)
        button.setOnClickListener {
            val currentPhotoId: Int = vPager.currentItem
            var buldleVar: Bundle = Bundle()
            buldleVar.putInt("current", currentPhotoId)
            parentFragmentManager.setFragmentResult("photoChange", buldleVar)

//            val imageChosen: ImageView = view.findViewById(R.id.imageViewChosenInF3)
            imageChosen.setImageResource(FragmentImage.imageArr[currentPhotoId])
            currentPhotoInx = currentPhotoId
//            Toast.makeText(requireContext(), "Selected: $currentPhotoId", Toast.LENGTH_SHORT).show()
        }
    }

    fun setCurrentImage() {
        val imageChosen: ImageView = requireView().findViewById(R.id.imageViewChosenInF3)
        imageChosen.setImageResource(FragmentImage.imageArr[currentPhotoInx])

        val currentPhotoId: Int = vPager.currentItem
        var buldleVar: Bundle = Bundle()
        buldleVar.putInt("current", currentPhotoId)
        parentFragmentManager.setFragmentResult("photoChange", buldleVar)

//            val imageChosen: ImageView = view.findViewById(R.id.imageViewChosenInF3)
        imageChosen.setImageResource(FragmentImage.imageArr[currentPhotoId])
        currentPhotoInx = currentPhotoId
//            Toast.makeText(requireContext(), "Selected: $currentPhotoId", Toast.LENGTH_SHORT).show()
    }
}