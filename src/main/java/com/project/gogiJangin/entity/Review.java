package com.project.gogiJangin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(name = "RV_TITLE")
    private String rvTitle;

    @Column(name = "RV_AF_GROUP_ID")
    private Long rvAfGroupId;

    @Column(name = "RV_STATUS")
    private String rvStatus;

    @Column(name = "RV_PLATFORM")
    private String rvPlatform;

    @Column(name = "RV_ORDER")
    private int rvOrder;

    @Builder
    public Review(String rvTitle,
                  Long rvAfGroupId,
                  String rvStatus,
                  String rvPlatform,
                  int rvOrder) {
        this.rvTitle = rvTitle;
        this.rvAfGroupId = rvAfGroupId;
        this.rvStatus = rvStatus;
        this.rvPlatform = rvPlatform;
        this.rvOrder = rvOrder;
    }
}
