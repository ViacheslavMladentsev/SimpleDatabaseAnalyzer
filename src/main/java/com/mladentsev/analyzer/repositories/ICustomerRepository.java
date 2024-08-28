package com.mladentsev.analyzer.repositories;

import com.mladentsev.analyzer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findAllByLastName(String lastName);

    @Query(value = "SELECT c.id, c.name, c.last_name FROM public.purchases AS p LEFT JOIN public.customers c on p.customer_id = c.id LEFT JOIN public.products pr on p.product_id = pr.id WHERE pr.title = ?1 GROUP BY 1, 2, 3 HAVING count(pr.title) >= ?2 ", nativeQuery = true)
    List<CustomerEntity> findAllByTitleAndCount(String title, Integer count);

}
