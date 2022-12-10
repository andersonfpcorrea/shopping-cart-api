package me.dio.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.enumaration.PaymentMethod;
import me.dio.sacola.model.Cart;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Restaurant;
import me.dio.sacola.repository.CartRepository;
import me.dio.sacola.repository.ItemRepository;
import me.dio.sacola.repository.ProductRepository;
import me.dio.sacola.resource.dto.ItemDto;
import me.dio.sacola.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Cart not found: " + id);
                }
        );
    }

    @Override
    public Cart closeCart(Long id, int paymentMethodNumber) {
        Cart cart = getCart(id);
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Include items in cart");
        }

        PaymentMethod paymentMethod = paymentMethodNumber == 0 ? PaymentMethod.MONEY : PaymentMethod.CARD;

        cart.setPaymentMethod(paymentMethod);
        cart.setClosed(true);

        return cartRepository.save(cart);
    }

    @Override
    public Item insertItem(ItemDto itemDto) {
        Cart cart = getCart(itemDto.getCartId());

        if (cart.isClosed()) {
            throw new RuntimeException("Cart not closed");
        }

        Item productToBeInserted = Item.builder()
                .quantity(itemDto.getQuantity())
                .cart(cart)
                .product(productRepository.findById(itemDto.getProductId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Product not found");
                        }
                ))
                .build();

        if (cart.getItems().isEmpty()) {
            cart.getItems().add(productToBeInserted);
        } else {
            Restaurant restaurant = cart.getItems().get(0).getProduct().getRestaurant();
            Restaurant restaurantOfToBeInserted = productToBeInserted.getProduct().getRestaurant();

            if (restaurant.equals(restaurantOfToBeInserted)) {
                cart.getItems().add(productToBeInserted);
            } else {
                throw new RuntimeException("Not possible to add products of different restaurants");
            }
        }

        List<Double> itemsPrice = new ArrayList<>();

        for(Item cartItem: cart.getItems()) {
            double itemTotalPrice = cartItem.getProduct().getUnitPrice() * cartItem.getQuantity();
            itemsPrice.add(itemTotalPrice);
        }

        double cartTotalPrice = itemsPrice.stream().mapToDouble(itemTotalPrice -> itemTotalPrice).sum();

        cart.setTotalPrice(cartTotalPrice);
        cartRepository.save(cart);

        return itemRepository.save(productToBeInserted);
        }


}
