package com.cbarnick.shoppinglist.service

import com.cbarnick.shoppinglist.model.Item
import com.cbarnick.shoppinglist.repository.ItemRepository
import com.cbarnick.shoppinglist.toList
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemService(val db: ItemRepository) {
    fun getAll(): List<Item> = db.findAll().toList()

    fun getById(id: String): Item? = db.findById(id).toList().firstOrNull()

    fun getByName(name: String): Item? = db.findAll().firstOrNull() { it.name == name }

    fun save(item: Item) {
        val queriedItem = getByName(item.name)
        queriedItem?.let {
            it.quantity++
            db.save(it)
        } ?: run {
            db.save(item)
        }
    }

    fun deleteAll() {
        db.deleteAll()
    }
}