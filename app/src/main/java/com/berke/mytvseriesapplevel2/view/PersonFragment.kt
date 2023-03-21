package com.berke.mytvseriesapplevel2.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.helpers.downloadFromUrl
import com.berke.mytvseriesapplevel2.helpers.placHolderProgressBar
import com.berke.mytvseriesapplevel2.viewModel.PeopleViewModel
import kotlinx.android.synthetic.main.fragment_person.*


class PersonFragment : Fragment() {

    private lateinit var peoplViewModel: PeopleViewModel
    private var idPeople = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            idPeople = PersonFragmentArgs.fromBundle(it).idPeople
        }

        peoplViewModel = ViewModelProviders.of(this).get(PeopleViewModel::class.java)
        peoplViewModel.refreshPeopleData(idPeople)

        observeLiveDataForPeopleData()
    }

    private fun observeLiveDataForPeopleData(){
        peoplViewModel.peopleList.observe(viewLifecycleOwner, Observer {
            val personİmage = it.image.original

            context?.let { personImageView.downloadFromUrl(personİmage, placHolderProgressBar(it)) }

            personNameTextView.text = it.name
            countryTextView.text = it.country.timezone
            genderTextView.text = it.gender
            birthdayTextView.text = it.birthday

            try {
                deathdayTextView.text = it.deathday.toString()
            }catch (e:Exception){
                deathdayTextView.visibility = View.GONE
                e.printStackTrace()
            }

        })
    }

}