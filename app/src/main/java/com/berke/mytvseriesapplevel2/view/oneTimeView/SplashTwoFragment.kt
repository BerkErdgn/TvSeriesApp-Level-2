package com.berke.mytvseriesapplevel2.view.oneTimeView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.berke.mytvseriesapplevel2.R
import kotlinx.android.synthetic.main.fragment_splash_two.*


class SplashTwoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_two, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationView2.setOnClickListener {
            val actionTwoToLogin = SplashTwoFragmentDirections.actionSplashTwoFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(actionTwoToLogin)
        }
    }
}