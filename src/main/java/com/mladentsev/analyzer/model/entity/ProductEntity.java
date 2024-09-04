package com.mladentsev.analyzer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 ** Класс, представляющий сущность в базе данных
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Double price;

}
