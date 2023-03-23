package com.berke.mytvseriesapplevel2.view

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.berke.mytvseriesapplevel2.R
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        singupButton.setOnClickListener {
            val actionLoginToSingUp = LoginFragmentDirections.actionLoginFragmentToSingUpFragment()
            Navigation.findNavController(it).navigate(actionLoginToSingUp)
        }

        loginButton.setOnClickListener {

            val email = loginTextView.text
            val password = passwordTextView.text
            if (password!= null && email!=null){
                if (password.length >8 && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    val actionLoginToApp = LoginFragmentDirections.actionLoginFragmentToSecondeGeneralFragment()
                    Navigation.findNavController(it).navigate(actionLoginToApp)
                }else{
                    Toast.makeText(this.context,"Wronge Entering",Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(this.context,"Please enter a value",Toast.LENGTH_LONG).show()
           }

        }
    }
}