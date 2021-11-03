package ru.aasmc.whishlist.app

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.aasmc.whishlist.DetailViewModel
import ru.aasmc.whishlist.MainViewModel
import ru.aasmc.whishlist.persistence.*

val appModule = module {

    single<Repository> { RepositoryImpl(get()) }

    single<WishlistDao> {
        Room.databaseBuilder(
            get(),
            WishlistDatabase::class.java, "wishlist_database.db"
        ).allowMainThreadQueries()
            .build().wishListDao()
    }

    viewModel { MainViewModel(get()) }

    viewModel { DetailViewModel(get()) }
}
