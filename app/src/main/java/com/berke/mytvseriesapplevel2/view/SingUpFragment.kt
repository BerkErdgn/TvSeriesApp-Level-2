package com.berke.mytvseriesapplevel2.view

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.berke.mytvseriesapplevel2.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sing_uo.*
import kotlinx.android.synthetic.main.fragment_sing_uo.view.*


class SingUpFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sing_uo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionSingUpToLogin = SingUpFragmentDirections.actionSingUpFragmentToSecondeGeneralFragment()

        backLoginScreen.setOnClickListener{
            Navigation.findNavController(it).navigate(actionSingUpToLogin)
        }

        sinupSingupButton.setOnClickListener {
            val email = singupemailText.text
            val password = singUppasswordTextView.text.toString()
            val verifiedPassword = verificationPassword.text.toString()
            println(email)
            println(password)
            println(verifiedPassword)
            if (email!=null && password!=null && verifiedPassword!=null){
                if(password == verifiedPassword){
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length>8){
                        Navigation.findNavController(it).navigate(actionSingUpToLogin)
                        }else{
                         Toast.makeText(this.context,"Place Enter Correct value",Toast.LENGTH_LONG).show()
                    }
                    }else{
                        Toast.makeText(this.context,"Password and Verified password are not the same",Toast.LENGTH_LONG).show()
                    }

            }else{
                Toast.makeText(this.context,"Please enter a value", Toast.LENGTH_LONG).show()
            }

        }

    }

}