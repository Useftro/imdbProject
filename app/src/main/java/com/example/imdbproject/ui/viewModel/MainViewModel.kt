package com.example.imdbproject.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.imdbproject.repository.MainRepository
import com.example.imdbproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel (private val mainRepository: MainRepository) : ViewModel() {
    fun getCharacters() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = mainRepository.getCharacters()))
        } catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message?:"Error occured $exception"))
        }
    }
}