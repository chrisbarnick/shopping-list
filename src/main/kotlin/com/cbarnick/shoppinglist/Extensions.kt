package com.cbarnick.shoppinglist

import java.util.*

fun <T : Any> Optional<out T>.toList(): List<T> =
    if (isPresent) listOf(get()) else emptyList()