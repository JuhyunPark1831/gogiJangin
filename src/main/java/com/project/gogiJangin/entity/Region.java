package com.project.gogiJangin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_REGION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RG_ID")
    public Long rgId;

    @Column(name = "RG_NAME")
    public String rgName;
}
