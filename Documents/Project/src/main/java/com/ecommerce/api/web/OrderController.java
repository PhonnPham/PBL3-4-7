package com.ecommerce.api.web;
import com.ecommerce.api.domain.CustomerOrder;
import com.ecommerce.api.repository.OrderRepository;
import com.ecommerce.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/orders") @RequiredArgsConstructor
public class OrderController {
    private final OrderService service; private final OrderRepository orders;
    @PostMapping("/checkout") public CustomerOrder checkout(Authentication auth) { return service.checkout(auth.getName()); }
    @GetMapping("/{id}") public CustomerOrder get(@PathVariable Long id, Authentication auth) {
        CustomerOrder order = orders.findById(id).orElseThrow();
        if (!order.getCustomerUsername().equals(auth.getName())) throw new org.springframework.security.access.AccessDeniedException("Not your order");
        return order;
    }
}
