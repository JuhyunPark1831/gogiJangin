package com.project.gogiJangin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false, name = "REG_DT")
    private LocalDateTime regDt;

    @LastModifiedDate
    @Column(name = "MOD_DT")
    private LocalDateTime modDt;

    @Column(name = "DEL_YN", nullable = false, length = 1)
    private String delYn = "N";

    public void softDelete() {
        this.delYn = "Y";
    }
}
