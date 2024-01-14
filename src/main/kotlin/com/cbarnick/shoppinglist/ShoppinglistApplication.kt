package com.cbarnick.shoppinglist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*

@SpringBootApplication
class ShoppinglistApplication

fun main(args: Array<String>) {
    runApplication<ShoppinglistApplication>(*args)
}

@RestController
class ItemController(val service: ItemService) {
	@GetMapping("/")
	fun index(): List<Item> = service.getItems()

	@PostMapping("/")
	fun post(@RequestBody item: Item) {
		service.save(item)
	}

	@DeleteMapping("/")
	fun delete() {
		service.deleteAll()
	}
}

@Table("ITEMS")
data class Item(@Id var id: String?, val name: String, var quantity: Int = 1)

@Service
class ItemService(val db: ItemRepository) {
	fun getItems(): List<Item> = db.findAll().toList()

	fun findItemById(id: String): List<Item> = db.findById(id).toList()

	fun save(item: Item) {
		val queriedItem = db.findAll().firstOrNull { it.name == item.name }
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

	fun <T : Any> Optional<out T>.toList(): List<T> =
			if (isPresent) listOf(get()) else emptyList()
}

interface ItemRepository : CrudRepository<Item, String>