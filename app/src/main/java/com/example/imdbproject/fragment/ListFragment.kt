package com.example.imdbproject.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbproject.BASE_URL
import com.example.imdbproject.R
import com.example.imdbproject.api.ApiRequests
import com.example.imdbproject.data.fromJsonToKotlin.Result

import com.example.imdbproject.data.fromJsonToKotlin.serverResponse
import com.example.imdbproject.ui.adapter.FragmentListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

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
        progressBar2.visibility = View.GONE

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

            Log.d("CHARACTERS", charactersArrayList.lastIndex.toString())

            itemsswipetorefresh.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(recyclerView.context, R.color.colorPrimary))
            itemsswipetorefresh.setColorSchemeColors(Color.WHITE)

            itemsswipetorefresh.setOnRefreshListener {
                    val data: serverResponse = getData()
                    Log.d("DATA", data.results.toString())
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = FragmentListAdapter(data.results)
                    itemsswipetorefresh.isRefreshing = false
            }
//            Log.d("SERVER", srvResp2[0].results.toString())
//            Log.d("PAGENUMBER", srvResp2[0].info.next[srvResp2[0].info.next.lastIndex].toString())

//            recyclerView.apply {
//                layoutManager = LinearLayoutManager(activity)
//                adapter = FragmentListAdapter(charactersArrayList)
//            }

//            lateinit var data: serverResponse
//
//            itemsswipetorefresh.setOnRefreshListener {
//                data = getData(srvResp2[0].info.next)
//                layoutManager = LinearLayoutManager(activity)
//                adapter = FragmentListAdapter(data.results)
//                itemsrv.adapter = adapter
//                itemsswipetorefresh.isRefreshing = false
//            }
        }
    }

    private var pagesCount: Int = 1
    private var listOfServerResponse: MutableList<serverResponse> = mutableListOf()

    fun getData(): serverResponse{
        var data = serverResponse()
        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequests::class.java)
        Log.d("PAGES_COUNT", pagesCount.toString())
        if(pagesCount <= 34) {
            Log.d("PASSEDPAGESCOUNT", "YES")
            GlobalScope.launch(Dispatchers.IO) {
                // Request takes only 20 chars per request, there are 671 chars
                val response = api.getCharacters(pagesCount).awaitResponse()
                Log.d("RESPONSE", response.body()!!.results.toString())
                if (response.isSuccessful) {
                    data = response.body()!!
                    listOfServerResponse.add(data)
                    Log.d("TAG", data.results.toString())
                }

                withContext(Dispatchers.Main) {
                    progressBar2.visibility = View.GONE
                }
            }
        }
        pagesCount += 1
        return data
    }
}