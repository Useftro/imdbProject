package com.example.imdbproject.ui.viewModel

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.liveData
//import com.example.imdbproject.data.repository.MainRepository
//import com.example.imdbproject.utils.Resource
//import kotlinx.coroutines.Dispatchers
//import java.lang.Exception
//
//class MainViewModel (private val mainRepository: MainRepository) : ViewModel() {
//    fun getResults() = liveData(Dispatchers.IO){
//        emit(Resource.loading(data = null))
//        try{
//            emit(Resource.success(data = mainRepository.getResults()))
//        } catch (exception: Exception){
//            emit(Resource.error(data = null, message = exception.message?:"Error occured $exception"))
//        }
//    }
//}