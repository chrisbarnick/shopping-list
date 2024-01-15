package com.cbarnick.shoppinglist.repository

import com.cbarnick.shoppinglist.model.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<Item, String>