package com.mladentsev.analyzer.repositories;

import com.mladentsev.analyzer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findAllByLastName(String lastName);

    @Query(value = "SELECT c.id, c.name, c.last_name \n" +
            "FROM public.purchases AS p \n" +
            "LEFT JOIN public.customers c on p.customer_id = c.id \n" +
            "LEFT JOIN public.products pr on p.product_id = pr.id \n" +
            "WHERE pr.title = ?1 \n" +
            "GROUP BY c.id, c.name, c.last_name \n" +
            "HAVING count(pr.title) >= ?2 ",
            nativeQuery = true)
    List<CustomerEntity> findAllByTitleAndCount(String title, Integer count);

    @Query(value = "SELECT c.id, c.name, c.last_name, sum(pr.price)\n" +
            "FROM public.customers AS c\n" +
            "LEFT JOIN public.purchases AS p on c.id = p.customer_id\n" +
            "LEFT JOIN public.products AS pr ON p.product_id = pr.id\n" +
            "GROUP BY c.id, c.name, c.last_name\n" +
            "HAVING sum(pr.price) BETWEEN ?1 AND ?2",
            nativeQuery = true)
    List<CustomerEntity> findCustomersWithTotalPurchaseCostInRange(Integer min, Integer max);

    @Query(value = "SELECT c.id, c.name, c.last_name\n" +
            "FROM public.purchases AS p\n" +
            "LEFT JOIN public.customers c on p.customer_id = c.id\n" +
            "GROUP BY c.id, c.name, c.last_name\n" +
            "ORDER BY count(*)\n" +
            "LIMIT ?1",
            nativeQuery = true)
    List<CustomerEntity> findCustomersWithLeastNumberOfPurchases(Integer countCustomers);

    @Query(value = "SELECT concat(c.name, ' ', c.last_name) AS name\n" +
            "FROM customers AS c\n" +
            "WHERE c.id = ?1",
            nativeQuery = true)
    String findFullNameById(Long id);


}
