package com.project.gogiJangin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Review {

    @Id
    @Column
    private String rvId;

    @Column
    private int star;

    @Column
    private String content;

    @Column
    private String user;
}
