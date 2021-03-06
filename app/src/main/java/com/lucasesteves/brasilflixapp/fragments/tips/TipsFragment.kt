package com.lucasesteves.brasilflixapp.fragments.tips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.lucasesteves.brasilflixapp.R
import com.lucasesteves.brasilflixapp.adapter.TipsVP
import com.lucasesteves.brasilflixapp.databinding.FragmentTipsBinding

class TipsFragment : Fragment() {

    private var binding: FragmentTipsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonHome?.setOnClickListener{
            findNavController().navigate(R.id.action_tipsFragment_to_HomeFragment)
        }

        val fragments = listOf(
            TipsImageFragment.newInstance(0),
            TipsImageFragment.newInstance(1)
        )
        val tipsViewPager = TipsVP(
            fragments,
            childFragmentManager
        )

        val viewPager = view.findViewById<ViewPager>(R.id.viewPagerImages)
        viewPager.adapter = tipsViewPager
        binding?.dotsIndicator?.setViewPager(viewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}