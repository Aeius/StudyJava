package com.gradleJSP.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "인덱스")
    private long num;
    @Column
    @Schema(description = "이름")
    private String name;
    @Column
    @Schema(description = "나이")
    private int age;

    @Builder
    public People(long num, String name, int age) {
        this.num = num;
        this.name = name;
        this.age = age;
    }
}
