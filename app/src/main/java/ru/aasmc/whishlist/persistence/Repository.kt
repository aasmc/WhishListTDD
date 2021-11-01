package ru.aasmc.whishlist.persistence

import androidx.lifecycle.LiveData
import ru.aasmc.whishlist.Wishlist

interface Repository {
  fun saveWishlist(wishlist: Wishlist)
  fun getWishlists(): LiveData<List<Wishlist>>
  fun getWishlist(id: Int): LiveData<Wishlist>
  fun saveWishlistItem(wishlist: Wishlist, name: String)
}