package com.mladentsev.analyzer.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
