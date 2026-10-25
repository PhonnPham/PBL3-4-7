package com.ecommerce.api.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) private CustomerOrder order;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) private Product product;
    @Column(nullable = false) private Integer quantity;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal unitPrice;
}
