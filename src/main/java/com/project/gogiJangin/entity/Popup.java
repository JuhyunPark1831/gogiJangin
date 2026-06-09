package com.project.gogiJangin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_POPUP")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Popup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PU_ID")
    private Long puId;

    @Column(name = "PU_TITME")
    private String puTitle;

    @Column(name = "PU_IMAGE_URL")
    private String puImageUrl;
}
