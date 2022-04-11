package aut.bme.hu.boltie.viewmodel

import androidx.lifecycle.ViewModel
import aut.bme.hu.boltie.model.Ware

class CurrentSaleSessionViewModel : ViewModel() {
    val wares = mutableListOf<Ware>()

    fun addWare(w: Ware) {
        // TODO
    }

    fun getPrice() {
        // TODO
    }
}