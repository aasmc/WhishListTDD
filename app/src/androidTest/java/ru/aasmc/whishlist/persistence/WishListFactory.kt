package ru.aasmc.whishlist.persistence

import ru.aasmc.whishlist.Wishlist
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object WishListFactory {
    private fun makeRandomString() = UUID.randomUUID().toString()

    private fun makeRandomInt() =
        ThreadLocalRandom.current().nextInt(0, 1000 + 1)

    fun makeWishlist(): Wishlist {
        return Wishlist(
            makeRandomString(),
            listOf(makeRandomString(), makeRandomString()),
            makeRandomInt()
        )
    }
}