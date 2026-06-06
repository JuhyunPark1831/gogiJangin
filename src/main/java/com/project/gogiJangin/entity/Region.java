package com.project.gogiJangin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Region {

    @Id
    @Column
    public String rgId;

    @Column
    public String regionName;
}
