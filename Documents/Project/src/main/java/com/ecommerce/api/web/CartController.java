package com.ecommerce.api.web;
import com.ecommerce.api.domain.CartItem;
import com.ecommerce.api.service.CartService;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/v1/cart") @RequiredArgsConstructor
public class CartController {
    private final CartService service;
    @GetMapping public List<CartItem> get(Authentication auth) { return service.get(auth.getName()); }
    @PostMapping("/items") public CartItem add(Authentication auth, @Valid @RequestBody AddItem request) { return service.add(auth.getName(), request.productId(), request.quantity()); }
    @DeleteMapping("/items/{productId}") public void remove(Authentication auth, @PathVariable Long productId) { service.remove(auth.getName(), productId); }
    public record AddItem(@NotNull Long productId, @Min(1) int quantity) {}
}
