package me.dio.sacola.resource;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.model.Cart;
import me.dio.sacola.model.Item;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartResource {
    private final CartService cartService;

    @PostMapping
    public Item insertItem(@RequestBody ItemDto itemDto) {
        return cartService.insertItem(itemDto);
    };

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable("id") Long id) {
        return cartService.getCart(id);
    }

    @PatchMapping("/closeCart/{id}")
    public Cart closeCart(@PathVariable("id") Long id,@RequestParam("paymentMethod") int paymentMethod) {
        return cartService.closeCart(id, paymentMethod);
    }
}
