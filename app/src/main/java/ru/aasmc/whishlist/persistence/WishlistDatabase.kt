package ru.aasmc.whishlist.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.aasmc.whishlist.Wishlist

@Database(entities = [Wishlist::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class WishlistDatabase : RoomDatabase() {

    abstract fun wishListDao(): WishlistDao

}
