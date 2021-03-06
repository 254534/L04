package com.example.l04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {
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
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class MyPagerAdapter3(fg: Fragment): FragmentStateAdapter(fg) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return Fragment21()
                1 -> return Fragment22.newInstance("f22", "Page # 2")
                2 -> return Fragment23.newInstance("f23", "Page # 3")
            }
            return Fragment21()
        }
        override fun getItemCount(): Int {
            return 3
        }

//    override fun getPageTitle(position: Int): CharSequence? {
//        return
//    }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vpAdapter = MyPagerAdapter3(this)

        val vPager = view.findViewById<ViewPager2>(R.id.vpager2Tabs)
        vPager.adapter = vpAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tabs2Tabs)

        TabLayoutMediator(tabLayout, vPager) { tab, position ->
            tab.text = "Tab ${(position + 1)}"
//            tab.setIcon(tabIcons[0])
        }.attach()

        childFragmentManager.setFragmentResultListener("hexBackground", viewLifecycleOwner) {
                requestKey, bundle ->
            parentFragmentManager.setFragmentResult(requestKey, bundle)
        }

        childFragmentManager.setFragmentResultListener("currentText", viewLifecycleOwner) {
                requestKey, bundle ->
            parentFragmentManager.setFragmentResult(requestKey, bundle)
        }
    }
}