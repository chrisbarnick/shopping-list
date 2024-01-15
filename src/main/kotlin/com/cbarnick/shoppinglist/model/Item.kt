package com.cbarnick.shoppinglist.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("ITEMS")
data class Item(@Id var id: String?, val name: String, var quantity: Int = 1, var checked: Boolean = false)