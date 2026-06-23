package com.project.gogiJangin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ATTACH_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AttachFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AF_ID")
    private Long afId;

    @Column(name = "AF_GROUP_ID")
    private Long afGroupId;

    @Column(name = "AF_FILE_ORI_NAME")
    private String afFileOriName;

    @Column(name = "AF_FILE_PATH")
    private String afFilePath;

    @Builder
    public AttachFile(Long afGroupId,
                      String afFileOriName,
                      String afFilePath) {
        this.afGroupId = afGroupId;
        this.afFileOriName = afFileOriName;
        this.afFilePath = afFilePath;
    }
}