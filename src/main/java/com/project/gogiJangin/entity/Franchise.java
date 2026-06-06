package com.project.gogiJangin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Franchise {

    @Id

    @Column
    private String frId;

    @Column
    private String name;

    @Column
    private String contact;

    @Column
    @
    private Region region;
}
