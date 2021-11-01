package ru.aasmc.whishlist.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WishListDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var wishListDatabase: WishlistDatabase
    private lateinit var wishListDao: WishlistDao

    @Before
    fun initDb() {
        wishListDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WishlistDatabase::class.java
        ).build()

        wishListDao = wishListDatabase.wishListDao()
    }

    @After
    fun closeDb() {
        wishListDatabase.close()
    }

}
























