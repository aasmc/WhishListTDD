package ru.aasmc.whishlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import ru.aasmc.whishlist.persistence.RepositoryImpl
import ru.aasmc.whishlist.persistence.WishlistDao
import ru.aasmc.whishlist.persistence.WishlistDaoImpl


class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val wishListDao: WishlistDao =
        Mockito.spy(
            WishlistDaoImpl()
        )

    private val viewModel =
        DetailViewModel(RepositoryImpl(wishListDao))

    @Test
    fun saveNewItemCallsDatabase() {
        viewModel.saveNewItem(
            Wishlist(
                "Victoria",
                listOf(
                    "RW Android Apprentice Book",
                    "Android phone"
                ),
                1
            ),
            "Smart watch"
        )
        verify(wishListDao).save(any())
    }

    @Test
    fun saveNewItemSavesData() {
        val wishList = Wishlist(
            "Victoria",
            listOf(
                "RW Android Apprentice Book",
                "Android phone"
            ),
            1
        )
        val name = "Smart watch"

        viewModel.saveNewItem(wishList, name)

        val mockObserver = mock<Observer<Wishlist>>()
        wishListDao.findById(wishList.id)
            .observeForever(mockObserver)

        verify(mockObserver).onChanged(
            wishList.copy(wishes = wishList.wishes + name)
        )
    }

    @Test
    fun getWishListCallsDatabase() {
        viewModel.getWishlist(1)

        verify(wishListDao).findById(any())
    }

    @Test
    fun getWishListReturnsCorrectData() {
        val wishList = Wishlist(
            "Victoria",
            listOf(
                "RW Android Apprentice Book",
                "Android phone"
            ),
            1
        )
        wishListDao.save(wishList)

        val mockObserver = mock<Observer<Wishlist>>()
        viewModel.getWishlist(1).observeForever(mockObserver)

        verify(mockObserver).onChanged(wishList)
    }
}























