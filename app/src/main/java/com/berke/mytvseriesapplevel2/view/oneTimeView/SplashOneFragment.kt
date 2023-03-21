package com.berke.mytvseriesapplevel2.view.oneTimeView

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import com.berke.mytvseriesapplevel2.R
import kotlinx.android.synthetic.main.fragment_splash_one.*


@Suppress("DEPRECATION")
class SplashOneFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_one, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animation()
        setTimer(view)


    }


    private fun setTimer (view: View){
        val timer = object : CountDownTimer(3500,1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                val actionOneToSecond = SplashOneFragmentDirections.actionSplashOneFragmentToSplashTwoFragment()
                Navigation.findNavController(view).navigate(actionOneToSecond)
            }

        }

        timer.start()
    }

    private fun animation (){
        val animation1 = AnimationUtils.loadAnimation(this.context,R.anim.animation1)
        nameTextView.animation= animation1

    }
}