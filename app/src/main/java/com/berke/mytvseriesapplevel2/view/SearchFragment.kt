package com.berke.mytvseriesapplevel2.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.berke.mytvseriesapplevel2.R
import com.berke.mytvseriesapplevel2.adapter.SearchTvSeriesAdapter
import com.berke.mytvseriesapplevel2.models.searchModels.SearchTvSeriesModels
import com.berke.mytvseriesapplevel2.viewModel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var searchTvSeriesAdapter = SearchTvSeriesAdapter (SearchTvSeriesModels())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        warningText.visibility = View.VISIBLE
        searchRecyclerView.visibility = View.GONE
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        searchButton.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                warningText.visibility = View.GONE
                searchRecyclerView.visibility = View.VISIBLE

                if (p0 ==""){
                    Toast.makeText(context,"Pleas Enter Tv Series Name !!",Toast.LENGTH_LONG).show()
                }else{
                    searchViewModel.refreshSearchData(p0!!)
                    searchRecyclerView.layoutManager = LinearLayoutManager(context)
                    searchRecyclerView.adapter = searchTvSeriesAdapter

                    ObserveLiveDataForSearch()

                }

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               return true
            }

        })
    }

    private fun ObserveLiveDataForSearch (){
        searchViewModel.searchTvSeriesList.observe(viewLifecycleOwner, Observer {
            searchTvSeriesAdapter.updateSearchTvSeriesList(it)
        })
    }

}