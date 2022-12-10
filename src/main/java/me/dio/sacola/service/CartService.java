package me.dio.sacola.service;

import me.dio.sacola.model.Cart;
import me.dio.sacola.model.Item;
import me.dio.sacola.resource.dto.ItemDto;

public interface CartService {
    Cart getCart(Long id);
    Cart closeCart(Long id, int paymentMethod);
    Item  insertItem(ItemDto itemDto);
}
