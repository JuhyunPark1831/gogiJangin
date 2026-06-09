package com.project.gogiJangin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_REVIEW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review extends BaseEntity {

    @Id
    @Column(name = "RV_ID")
    private Long rvId;

    @Column(name = "RV_STAR")
    private int rvStar;

    @Column(name = "RV_CONTENT")
    private String rvContent;

    @Column(name = "RV_WRITE_NAME")
    private String rvWriteName;
}
