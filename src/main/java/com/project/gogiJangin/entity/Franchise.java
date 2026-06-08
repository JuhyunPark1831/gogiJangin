package com.project.gogiJangin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long frId;

    @Column
    private String name;

    @Column
    private String contact;

    @ManyToOne
    @JoinColumn(name = "rg_id")
    private Region region;
}
