package com.mladentsev.analyzer.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity(name = "products")
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Double price;

}
