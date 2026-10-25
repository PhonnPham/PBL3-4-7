package com.ecommerce.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor
public class Product implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable = false)
    private String name;
    @Column(length = 2000)
    private String description;
    @NotNull @Positive @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
    @NotNull @PositiveOrZero @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
    private boolean active = true;
}
