package com.ecommerce.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username", "product_id"}))
@Getter @Setter @NoArgsConstructor
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String username;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) private Product product;
    @Column(nullable = false) private Integer quantity;
}
