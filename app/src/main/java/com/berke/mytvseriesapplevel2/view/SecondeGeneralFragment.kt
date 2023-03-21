package com.berke.mytvseriesapplevel2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.replace
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.databinding.FragmentSecondeGeneralBinding


class SecondeGeneralFragment : Fragment() {

    private var _binding : FragmentSecondeGeneralBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate the layout and bind to the _binding
        _binding = FragmentSecondeGeneralBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        replaceFragment(AllTvSeriesFragment())
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomMenu.setOnItemSelectedListener {
            when (it) {
                R.id.home -> replaceFragment(AllTvSeriesFragment())
                R.id.search-> replaceFragment(SearchFragment())
                R.id.user-> replaceFragment(UserFragment())
                else -> {
                }

            }
            true
        }

    }


    private fun replaceFragment (fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransection = fragmentManager.beginTransaction()
        fragmentTransection.replace(R.id.meanFrameLayout,fragment)
        fragmentTransection.commit()
    }


}