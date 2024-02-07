package org.example.repository;

import org.example.domain.ForSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForSaleRepository extends JpaRepository<ForSale, Long> {
    // User ID로 매물 find
    List<ForSale> findAllByUser_Id(Long id);
}
