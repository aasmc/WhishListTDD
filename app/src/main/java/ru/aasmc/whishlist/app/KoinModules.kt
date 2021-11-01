package ru.aasmc.whishlist.app

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.aasmc.whishlist.DetailViewModel
import ru.aasmc.whishlist.MainViewModel
import ru.aasmc.whishlist.persistence.Repository
import ru.aasmc.whishlist.persistence.RepositoryImpl
import ru.aasmc.whishlist.persistence.WishlistDao
import ru.aasmc.whishlist.persistence.WishlistDaoImpl

val appModule = module {

    single<Repository> { RepositoryImpl(get()) }

    single<WishlistDao> { WishlistDaoImpl() }

    viewModel { MainViewModel(get()) }

    viewModel { DetailViewModel(get()) }
}
