package com.gradleJSP.demo.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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
    @ApiParam(name = "index", readOnly = true)
    private long num;
    @Column
    @ApiParam(name = "이름", example = "세글자")
    private String name;
    @Column
    @ApiParam(name = "나이", example = "20")
    private int age;

    @Builder
    public People(long num, String name, int age) {
        this.num = num;
        this.name = name;
        this.age = age;
    }
}
