package com.ecommerce.api.repository;
import com.ecommerce.api.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {}
