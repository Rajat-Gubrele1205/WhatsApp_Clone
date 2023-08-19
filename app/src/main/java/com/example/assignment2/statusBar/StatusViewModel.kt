package com.example.assignment2.statusBar


import ProductResponseData
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2.network.StatusApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface NetworkState {
    data class Success(val data: ProductResponseData) : NetworkState
    object Error : NetworkState
    object Loading : NetworkState
}

class StatusViewModel(private val dao: StatusDao) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    private val _networkState = MutableStateFlow<NetworkState>(NetworkState.Loading)
    val networkState: StateFlow<NetworkState> = _networkState.asStateFlow()

    init {
        getAllData()
        updateAllData()
    }

    val allProducts: Flow<List<ProductEntity>> = dao.getALlProducts()


    private fun getAllData() {
        viewModelScope.launch {
            _networkState.value = try {
                val listResult = StatusApi.retrofitService.getProducts()
                Log.d("CheckAPi", "getAllData ")
                NetworkState.Success(listResult)

            } catch (e: IOException) {
                NetworkState.Error
            }
        }
    }

    private fun updateAllData() {
        viewModelScope.launch {

            dao.deleteEntity()
            _networkState.collect {
                if (it is NetworkState.Success) {
                    Log.d("CheckAPi", "update ")
                    val productEntityList = setAllData(it.data.products)
                    productEntityList.forEach { value ->
                        dao.upsertEntity(value)
                    }
                    Log.d("CheckAPi", "done ")
                }
            }

        }
    }

    fun checkDatabase() {
        viewModelScope.launch {

        }
    }

//    val delete = viewModelScope.async {
//        dao.getALlProducts().collect {
//            it.forEach { value ->
//                dao.deleteEntity(value)
//            }
//        }
//    }

    fun deleteAllData() {
    }

}
