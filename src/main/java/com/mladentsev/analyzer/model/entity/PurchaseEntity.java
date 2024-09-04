package com.mladentsev.analyzer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 ** Класс, представляющий сущность в базе данных
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "purchases")
@Table(name = "purchases")
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CustomerEntity customer;

    @ManyToOne
    private ProductEntity product;

    @Column(nullable = false)
    private LocalDate date;
}
