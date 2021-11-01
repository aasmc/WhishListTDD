package ru.aasmc.whishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.aasmc.whishlist.persistence.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

  fun saveNewList(name: String) {
      repository.saveWishlist(Wishlist(name, listOf()))
  }

  fun getWishlists(): LiveData<List<Wishlist>> {
    return repository.getWishlists()
  }
}