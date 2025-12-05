package com.ex.subscribe.category;

import com.ex.subscribe.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "categories")
@Getter
public class CategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;
}
