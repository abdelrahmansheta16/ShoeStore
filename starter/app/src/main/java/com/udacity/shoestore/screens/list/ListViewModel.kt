package com.udacity.shoestore.screens.list

/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


/**
 * ViewModel containing all the logic needed to run the game
 */
class ListViewModel : ViewModel() {

    // Shoe
    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe> get() = _shoe

    // The current inventory of shoes
    private var _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    private var _images = MutableLiveData<MutableList<String>>()
    val images: LiveData<MutableList<String>>
        get() = _images

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _size = MutableLiveData<String>()
    val size: LiveData<String>
        get() = _size

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private val _company = MutableLiveData<String>()
    val company: LiveData<String>
        get() = _company

    private val _returnToList = MutableLiveData<Boolean>()
    val returnToList: LiveData<Boolean>
        get() = _returnToList

    init {
        _shoe.value = Shoe("", 0.0, "","")
        _shoes.value = shoeList()
        _returnToList.value = false
    }

    /**
     * Resets the list of words and randomizes the order
     */
    fun addShoe() {
        _shoes.value?.add(_shoe.value!!)
        _returnToList.value = true
        _shoe.value = Shoe("", 0.0, "","")
    }

    fun resetReturnToList() {
        _returnToList.value = false
        _shoe.value?.name = ""
        _shoe.value?.size = 0.0
        _shoe.value?.company = ""
        _shoe.value?.description = ""
    }

    /**
     * Initializes the list of shoes i.e. initial stock of shoes in inventory
     */
    private fun shoeList() : MutableList<Shoe> {
        return mutableListOf(
            Shoe("Air Jordan", 8.5, "Nike",
                "The classic produced for basketball legend Michael Jordan.",
                mutableListOf("Nike Air Jordan")),
            Shoe("Samba", 8.0, "Adidas",
                "One of Adidas' most popular shoes. An ageless classic.",
                mutableListOf("Adidas Samba")),
            Shoe("Chuck Taylor All-Stars", 7.5, "Converse",
                "Converse's signature basketball sneakers for the filthy casual.",
                mutableListOf("Converse Chuck Taylor All-Stars")),
            Shoe("Hangisi", 7.0, "Manolo Blahnik",
                "Turkish inspired. Romantic and artistic. A pair of quality heels.",
                mutableListOf("Manolo Blahnik Hangisi")),
            Shoe("Fetto", 8.0, "Jimmy Choo", "Star-studded and glamourous.",
                mutableListOf("Jimmy Choo Fetto")),
            Shoe("Pigalle", 8.0, "Christian Louboutin",
                "Elegant and timeless, an essential addition to every shoe lovers' collection",
                mutableListOf("Christian Louboutin Pigalle"))
        )
    }


}
