package aut.bme.hu.boltie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import aut.bme.hu.boltie.model.User

class FunctionSelectViewModel(

) : ViewModel() {
    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }
}