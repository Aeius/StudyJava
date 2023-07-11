package com.gradleJSP.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long num;
    @Column
    private String name;
    @Column
    private int age;

    @Builder
    public People(long num, String name, int age) {
        this.num = num;
        this.name = name;
        this.age = age;
    }
}
