package com.example.imdbproject.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbproject.R
import com.example.imdbproject.data.fromJsonToKotlin.Result

import com.example.imdbproject.data.fromJsonToKotlin.serverResponse
import com.example.imdbproject.ui.adapter.FragmentListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = this.arguments
        val charactersArrayList: ArrayList<Result> = ArrayList()
        val srvResp2 = args?.getParcelableArrayList<serverResponse>("data")
        if (args != null) {

            Log.d("Bla", srvResp2?.lastIndex.toString())

            for (charsList in srvResp2!!){
                for(character in charsList.results){
                    charactersArrayList.add(character)
                }
            }


            recyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = FragmentListAdapter(charactersArrayList)
            }
        }
    }
}