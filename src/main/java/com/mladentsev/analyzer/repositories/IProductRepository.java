package com.mladentsev.analyzer.repositories;

import com.mladentsev.analyzer.model.entity.CustomerEntity;
import com.mladentsev.analyzer.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT pr.*\n" +
            "FROM public.purchases AS p\n" +
            "LEFT JOIN public.products AS pr on p.product_id = pr.id\n" +
            "WHERE p.customer_id = ?1 AND p.date BETWEEN ?2 AND ?3",
            nativeQuery = true)
    List<ProductEntity> findAllProductAndPriceByCustomerIdBetweenDate(Long id, Date startDate, Date endDate);
}
