package com.ecommerce.api.service;

import com.ecommerce.api.domain.*;
import com.ecommerce.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor
public class CartService {
    private final CartItemRepository carts;
    private final ProductRepository products;
    @Transactional
    public CartItem add(String username, Long productId, int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be positive");
        Product product = products.findById(productId).orElseThrow();
        CartItem item = carts.findByUsernameAndProductId(username, productId).orElseGet(CartItem::new);
        item.setUsername(username); item.setProduct(product);
        item.setQuantity(item.getQuantity() == null ? quantity : item.getQuantity() + quantity);
        return carts.save(item);
    }
    public List<CartItem> get(String username) { return carts.findByUsername(username); }
    @Transactional public void remove(String username, Long productId) {
        carts.findByUsernameAndProductId(username, productId).ifPresent(carts::delete);
    }
}
