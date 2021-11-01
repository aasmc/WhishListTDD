package ru.aasmc.whishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.aasmc.whishlist.persistence.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun saveNewItem(wishlist: Wishlist, name: String) {
        repository.saveWishlistItem(
            wishlist, name
        )
    }

    fun getWishlist(id: Int): LiveData<Wishlist> {
        return repository.getWishlist(id)
    }
}