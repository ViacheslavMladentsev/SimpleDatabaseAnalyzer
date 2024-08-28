package com.mladentsev.analyzer.repositories;

import com.mladentsev.analyzer.model.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
}
