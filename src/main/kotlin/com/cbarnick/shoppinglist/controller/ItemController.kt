package com.cbarnick.shoppinglist.controller

import com.cbarnick.shoppinglist.model.Item
import com.cbarnick.shoppinglist.service.ItemService
import org.springframework.web.bind.annotation.*

@RestController
class ItemController(val service: ItemService) {
    @GetMapping("/")
    fun index(): List<Item> = service.getAll()

    @PostMapping("/")
    fun post(@RequestBody item: Item) = service.save(item)

    @DeleteMapping("/")
    fun delete() = service.deleteAll()
}