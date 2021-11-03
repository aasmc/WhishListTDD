package ru.aasmc.whishlist.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import ru.aasmc.whishlist.Wishlist

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

    @Test
    fun getAllReturnsEmptyList() {
        val testObserver: Observer<List<Wishlist>> = mock()
        wishListDao.getAll().observeForever(testObserver)
        verify(testObserver).onChanged(emptyList())
    }

    @Test
    fun saveWishListSavesData() {
        val wishList1 = WishListFactory.makeWishlist()
        val wishList2 = WishListFactory.makeWishlist()
        wishListDao.save(wishList1, wishList2)

        val testObserver: Observer<List<Wishlist>> = mock()
        wishListDao.getAll().observeForever(testObserver)

        // The following 2 lines create an ArgumentCaptor to capture value in onChange of the
        // Observer<List<Wishlist>>. ArgumentCaptor from Mockito allows for making more complex
        // assertions on a value than equals
        val listClass = ArrayList::class.java as Class<ArrayList<Wishlist>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)
        verify(testObserver).onChanged(argumentCaptor.capture())
        assertTrue(argumentCaptor.value.size > 0)
    }

    @Test
    fun getAllRetrievesData() {
        val wishList1 = WishListFactory.makeWishlist()
        val wishList2 = WishListFactory.makeWishlist()
        wishListDao.save(wishList1, wishList2)

        val testObserver: Observer<List<Wishlist>> = mock()
        wishListDao.getAll().observeForever(testObserver)

        val listClass = ArrayList::class.java as Class<ArrayList<Wishlist>>
        val argumentCaptor = ArgumentCaptor.forClass(listClass)
        verify(testObserver).onChanged(argumentCaptor.capture())
        val capturedArgument = argumentCaptor.value
        assertTrue(capturedArgument.containsAll(listOf(wishList1, wishList2)))
    }

    @Test
    fun findByIdRetrievesCorrectData() {
        val wishList1 = WishListFactory.makeWishlist()
        val wishList2 = WishListFactory.makeWishlist()
        wishListDao.save(wishList1, wishList2)

        val testObserver: Observer<Wishlist> = mock()

        wishListDao.findById(wishList2.id).observeForever(testObserver)
        verify(testObserver).onChanged(wishList2)
    }

}


































