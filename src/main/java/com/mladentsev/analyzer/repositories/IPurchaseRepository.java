package com.mladentsev.analyzer.repositories;

import com.mladentsev.analyzer.model.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 ** Класс, описывающий набор запросов в базу данных для сущности PurchaseEntity.
 */

@Repository
public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    @Query(value = "SELECT DISTINCT p.customer_id\n" +
            "FROM public.purchases AS p\n" +
            "WHERE p.date BETWEEN ?1 AND ?2",
            nativeQuery = true)
    List<Long> findDistinctIdByDateTimeBetween(Date startDate, Date endDate) throws SQLException;

    @Query(value = "SELECT sum(pr.price)\n" +
            "FROM public.purchases AS p\n" +
            "         LEFT JOIN public.products AS pr ON p.product_id = pr.id\n" +
            "WHERE p.date BETWEEN ?1 AND ?2",
            nativeQuery = true)
    Double findTotalPriceAllPurchasesBetweenDate(Date startDate, Date endDate) throws SQLException;

}
