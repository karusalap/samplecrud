package com.pavan.samplecrud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    @NotBlank
    private String code;
    @Column(nullable = false)
    @Size(min = 3, max = 5)
    private String name;
    @Column(length = 5)
    private String organisation;
    @Column(precision = 2)
    @Min(1)
    private Double salary;
    @Column(length = 1)
    private String grade;
}
