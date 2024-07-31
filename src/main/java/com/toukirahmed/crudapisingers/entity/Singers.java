package com.toukirahmed.crudapisingers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Singers {
    @Id
    @Column(name = "singerpos", length = 3)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer singersPosition;
    @Column(name = "singername", length = 15)
    private String singersName;
    @Column(name = "signerremuner", length = 15)
    private Double singersRemuneration;
}
