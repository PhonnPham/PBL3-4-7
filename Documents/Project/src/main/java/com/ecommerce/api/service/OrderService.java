package com.ecommerce.api.service;

import com.ecommerce.api.domain.*;
import com.ecommerce.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service @RequiredArgsConstructor
public class OrderService {
    private final CartItemRepository carts;
    private final ProductRepository products;
    private final OrderRepository orders;
    private final TransactionRepository transactions;

    @Transactional
    public CustomerOrder checkout(String username) {
        var cart = carts.findByUsername(username);
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");
        CustomerOrder order = new CustomerOrder();
        order.setCustomerUsername(username);
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : cart) {
            Product product = products.findForUpdate(cartItem.getProduct().getId()).orElseThrow();
            if (product.getStock() < cartItem.getQuantity()) throw new IllegalStateException("Insufficient stock for " + product.getName());
            product.setStock(product.getStock() - cartItem.getQuantity());
            OrderItem item = new OrderItem();
            item.setOrder(order); item.setProduct(product); item.setQuantity(cartItem.getQuantity()); item.setUnitPrice(product.getPrice());
            order.getItems().add(item);
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        order.setTotal(total); order.setStatus(CustomerOrder.Status.PAID);
        CustomerOrder saved = orders.save(order);
        Transaction transaction = new Transaction();
        transaction.setOrderId(saved.getId()); transaction.setAmount(total); transaction.setStatus(Transaction.Status.COMPLETED);
        transactions.save(transaction);
        carts.deleteAll(cart);
        return saved;
    }
}
