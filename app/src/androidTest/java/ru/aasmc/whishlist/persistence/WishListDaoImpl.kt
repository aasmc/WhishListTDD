package ru.aasmc.whishlist.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import ru.aasmc.whishlist.Wishlist

open class WishlistDaoImpl : WishlistDao {
    private val wishlists = MutableLiveData<List<Wishlist>>(listOf())

    override fun getAll(): LiveData<List<Wishlist>> {
        return wishlists
    }

    override fun findById(id: Int): LiveData<Wishlist> {
        return Transformations.map(wishlists) {
            it.find { wishlist -> wishlist.id == id }
        }
    }

    override fun save(vararg wishlist: Wishlist) {
        wishlists.postValue(wishlists.value!!.toMutableList() + wishlist)
    }
}